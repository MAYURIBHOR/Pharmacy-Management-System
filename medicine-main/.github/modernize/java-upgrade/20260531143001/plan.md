# Upgrade Plan: Pharmacy Management System (20260531143001)

- **Generated**: 2026-05-31 14:30
- **HEAD Branch**: modernize/java-20260531195841
- **HEAD Commit ID**: (checked via git status)

## Available Tools

**JDKs**
- JDK 25.0.1: C:\Program Files\Java\jdk-25\bin (available on system)
- JDK 21: **<TO_BE_INSTALLED>** (required by upgrade step 3)
- JDK 17: not available (baseline will be skipped)

**Build Tools**
- Maven 3.9.12: C:\Program Files\apache-maven-3.9.12\bin
- Maven Wrapper: not present (Maven CLI will be used directly)

## Guidelines

- Minimal changes approach: only upgrade dependencies essential for Java 21 compatibility
- No Maven wrapper to maintain; use system Maven directly
- Preserve existing security configuration (already modern Spring Security 6+ DSL)
- Maintain backward compatibility where possible within Spring Boot 3.5.x line
- Focus on compile and test success with minimal API changes

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: modernize/java-20260531195841
- Run tests before and after the upgrade: true

## Upgrade Goals

| Component | Target Version | Reason |
|-----------|----------------|--------|
| Java | 21 | User selected: Latest LTS, current standard |
| Spring Boot | 3.5.x | User selected: Recommended for minimal breaking changes |

## Technology Stack

| Technology/Dependency | Current | Min Compatible | Why Incompatible |
|----------------------|---------|-----------------|------------------|
| Java | 17 | 21 | User requested |
| Spring Boot | 3.5.7 | 3.5.7 | Already at compatible version |
| Spring Framework | 6.1.x | 6.1.x | Managed by Spring Boot 3.5.7 |
| Maven | 3.9.12 | 3.9.0 | Good - already 3.9+ |
| maven-compiler-plugin | (default 3.11+) | 3.11.0 | Recommended for Java 21 support |
| Spring Security | 6.1.x | 6.1.x | Managed by Spring Boot - already compatible |
| Jakarta EE | 10 | 10 | Managed by Spring Boot 3.5.7 - already Jakarta |
| H2 Database | (default) | (default) | Managed by Spring Boot - compatible |
| Thymeleaf | 3.1.x | 3.1.x | Managed by Spring Boot - compatible |
| Lombok | 1.18.x | 1.18.x | Managed by Spring Boot - compatible |

## Derived Upgrades

None required. Spring Boot 3.5.7 is designed to work seamlessly with Java 21, and the project already uses:
- Jakarta EE (not javax) - no namespace migration needed
- Modern Spring Security DSL - no API migration needed
- No internal JDK APIs or reflection patterns that would break with Java 21

## Impact Analysis

### Subsection: Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
|------|-----------|---------|--------|--------|--------|
| pom.xml | java.version property | 17 | upgrade | 21 | User requested Java 21 upgrade |

### Subsection: Source Code Changes

No source code changes required. The project:
- Uses modern Spring Security 6+ DSL (lambda-based)
- Does not use javax.* imports (already using jakarta.*)
- Does not use internal JDK APIs (sun.*, jdk.internal.*, etc.)
- Does not use reflection-based code requiring `setAccessible()` on JDK classes
- Has no hardcoded Java version assumptions

### Subsection: Configuration Changes

No configuration changes required. Application properties are compatible with Java 21.

### Subsection: CI/CD Changes

No CI/CD files detected in the repository. If Docker or other CI configurations are added later, ensure:
- Dockerfile uses Java 21 base image: `openjdk:21-jdk-slim` or later
- Any build pipelines reference Java 21 where version is specified

### Subsection: Risks & Warnings

None identified. This is a low-risk upgrade:
- No major framework migrations required (already on Spring Boot 3.5.x with Jakarta EE)
- No reflection-based code that would fail with strong module encapsulation
- No deprecated APIs being used
- Modern Spring Security implementation already in place
- Minimal dependencies reduce compatibility risk

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Install required Java 21 JDK to enable compilation and testing with target version
  - **Changes to Make**: Install JDK 21 from trusted source; verify availability
  - **Verification**: `#appmod-list-jdks` must show JDK 21 available

- Step 2: Setup Baseline
  - **Rationale**: Baseline is SKIPPED because Java 17 is not available on system. The project will proceed directly to upgrade steps. Note: If Java 17 were available, this step would run `mvn clean compile test-compile -q && mvn clean test -q` to establish baseline pass rate.
  - **Changes to Make**: (None - skipped)
  - **Verification**: Confirmed via system JDK scan

- Step 3: Update Java Version to 21
  - **Rationale**: Update the project JDK target to 21 as requested by user. This is the core upgrade step.
  - **Changes to Make**: 
    - Update `java.version` property in pom.xml from 17 to 21
    - Maven compiler plugin will inherit this property and compile for Java 21 target
  - **Verification**: 
    - Command: `mvn clean test-compile -q` with JDK 21
    - Expected: Project compiles successfully with all source and test classes
    - JDK: C:\Program Files\Java\jdk-25\bin (Java 25 is compatible and can target Java 21 bytecode)

- Step 4: Final Validation
  - **Rationale**: Verify all tests pass with Java 21 and no breaking changes occurred during upgrade
  - **Changes to Make**: (No code changes - verification only)
  - **Verification**:
    - Command: `mvn clean test -q`
    - Expected Result: All tests pass (≥ baseline if baseline was run, or 100% for fresh run)
    - Resolve ANY failing tests via code fixes (no deferring)
  - **Success Criteria**: 
    - ✅ Compilation success with Java 21
    - ✅ 100% test pass rate
    - ✅ No warnings or errors during build
