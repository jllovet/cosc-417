---
name: loe-labeler
description: Find issues missing LOE labels and estimate/apply appropriate sizing
user-invocable: true
argument-hint: "[team or issue-id]"
allowed-tools: Bash(linear:*), Read, Grep, Glob
---

# LOE Labeler

Estimate and apply Level of Effort (LOE) labels to issues.

## When to Invoke

- Backlog grooming sessions
- Sprint/cycle planning
- When creating new issues
- Periodic cleanup of unlabeled issues

## LOE Scale

| Label | Duration | Examples |
|-------|----------|----------|
| `loe-xs` | < 1 hour | Config change, typo fix, simple doc update |
| `loe-small` | 1-4 hours | Single-file change, simple feature, bug fix |
| `loe-med` | 1-2 days | Multi-file feature, moderate complexity |
| `loe-large` | 3-5 days | Significant feature, multiple components |
| `loe-xl` | 1+ week | Major feature, architectural change |

## Estimation Factors

| Factor | Impact on Estimate |
|--------|-------------------|
| **Files affected** | More files = larger LOE |
| **Dependencies** | External deps = uncertainty buffer |
| **Testing complexity** | Test-heavy = larger LOE |
| **Domain knowledge** | Unfamiliar area = larger LOE |
| **Risk level** | High risk = larger LOE |

## Process

### 1. Find Unlabeled Issues

```bash
# List all issues without LOE labels
linear issues list --team TU --state "Backlog"
linear issues list --team TU --state "Todo"
```

Filter manually for issues missing `loe-*` labels.

### 2. Analyze Each Issue

For each unlabeled issue:

1. **Read the issue:**
   ```bash
   linear issues get --id TU-123
   ```

2. **Identify affected code:**
   - Search for relevant files
   - Check existing implementations
   - Note dependencies

3. **Estimate using factors:**
   - Count affected files
   - Assess testing needs
   - Consider domain familiarity
   - Factor in risk

### 3. Apply Labels

```bash
linear issues assign label --issue TU-123 --to "loe-small"
```

### 4. Document Rationale (Optional)

For non-obvious estimates, add a comment:

```bash
linear issues comment --id TU-123 --body "LOE: small

Rationale:
- Single file change (handlers/user.go)
- Existing test coverage
- Well-understood domain
- Low risk"
```

## Estimation Tips

### Bias Correction

| Bias | Mitigation |
|------|------------|
| Optimism | Add 20% buffer for unknowns |
| Anchoring | Estimate before checking similar issues |
| Scope creep | Re-estimate if scope changes |

### PERT Estimation (for uncertain tasks)

```
Expected = (Optimistic + 4*Likely + Pessimistic) / 6
```

Use when uncertainty is high to get a more realistic estimate.

## Output Format

```markdown
## LOE Labeling Report

### Issues Labeled

| Issue | Title | LOE | Rationale |
|-------|-------|-----|-----------|
| TU-123 | Add auth | small | Single endpoint, clear requirements |
| TU-124 | Refactor DB | large | Multiple tables, migration needed |

### Summary
- Total issues labeled: X
- XS: X | Small: X | Med: X | Large: X | XL: X

### Notes
[Any observations about patterns or concerns]
```
