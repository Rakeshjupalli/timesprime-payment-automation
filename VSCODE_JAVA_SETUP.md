# VSCode Java Setup Guide

## Issue: Java Version Compatibility Error

If you're getting the error:
```
java.lang.UnsupportedClassVersionError: org/testng/ITestNGListener has been compiled by a more recent version of the Java Runtime (class file version 55.0), this version of the Java Runtime only recognizes class file versions up to 52.0
```

This means VSCode is trying to run tests with Java 8, but TestNG requires Java 11 or higher.

## Solution

### 1. Update VSCode Settings

Create or update `.vscode/settings.json` with the following configuration:

```json
{
    "java.compile.nullAnalysis.mode": "automatic",
    "java.configuration.updateBuildConfiguration": "interactive",
    "java.jdt.ls.java.home": "/Library/Java/JavaVirtualMachines/temurin-11.jdk/Contents/Home",
    "java.configuration.runtimes": [
        {
            "name": "JavaSE-11",
            "path": "/Library/Java/JavaVirtualMachines/temurin-11.jdk/Contents/Home",
            "default": true
        },
        {
            "name": "JavaSE-1.8",
            "path": "/Library/Java/JavaVirtualMachines/temurin-8.jdk/Contents/Home"
        }
    ]
}
```

### 2. Reload VSCode

After updating the settings:
1. Press `Cmd+Shift+P` (Mac) or `Ctrl+Shift+P` (Windows/Linux)
2. Type "Reload Window"
3. Select "Developer: Reload Window"

This will restart the Java Language Server with the correct Java version.

### 3. Running Tests

#### From VSCode:
- Right-click on a test method → "Run Test"
- Or click the "Run Test" button that appears above test methods

#### From Terminal:
```bash
# Run specific test class
JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-11.jdk/Contents/Home mvn test -Dtest=NetBankingPaymentFlowTest

# Run all tests
JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-11.jdk/Contents/Home mvn test
```

## Verify Java Configuration

Check which Java version VSCode is using:
1. Open VSCode Command Palette (`Cmd+Shift+P`)
2. Type "Java: Configure Java Runtime"
3. Verify Java 11 is set as default

## Alternative: Set System JAVA_HOME

Add to your `~/.zshrc` or `~/.bash_profile`:
```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-11.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
```

Then reload your shell:
```bash
source ~/.zshrc
```

## Troubleshooting

If issues persist:
1. Clean and rebuild the project:
   ```bash
   mvn clean install
   ```

2. Delete VSCode Java extension data:
   - Close VSCode
   - Delete `~/Library/Application Support/Code/User/workspaceStorage/*` (Mac)
   - Restart VSCode

3. Check installed Java versions:
   ```bash
   /usr/libexec/java_home -V
   ```

4. Verify Maven is using correct Java:
   ```bash
   mvn -version
