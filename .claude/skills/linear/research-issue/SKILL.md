---
name: research-issue
description: Research an issue following the deep research protocol
user-invocable: true
disable-model-invocation: false
argument-hint: "<issue-id>"
allowed-tools: Bash(linear:*), Read, Grep, Glob, WebSearch, WebFetch
---

# Research Issue

Research an issue and produce actionable findings.

## Arguments

Issue ID (e.g., `TU-123`)

## Process

### 1. Get Issue Context

```bash
linear issues get --id $ARGUMENTS
linear issues comments --id $ARGUMENTS
```

### 2. Understand the Scope

- Read the issue description carefully
- Check parent/child issues
- Identify the key questions to answer

### 3. Prioritize by Information Gain

Before diving into research, estimate which sub-questions would produce the most information gain if answered.

#### Estimate Uncertainty Per Sub-Question

| Sub-Question | Current Confidence | Entropy | Priority |
|-------------|-------------------|---------|----------|
| [Question 1] | [0-1] | [lookup] | [High/Med/Low] |
| [Question 2] | [0-1] | [lookup] | [High/Med/Low] |

#### Entropy Lookup

| Confidence | Entropy (bits) | Priority |
|------------|---------------|----------|
| 50% | 1.00 | Highest - maximum uncertainty |
| 60% or 40% | 0.97 | Very high |
| 70% or 30% | 0.88 | High |
| 80% or 20% | 0.72 | Moderate |
| 90% or 10% | 0.47 | Low - fairly certain |
| 95% or 5% | 0.29 | Very low - skip unless critical |

**Prioritize:**
1. High entropy (most uncertain)
2. High impact (changes the recommendation)
3. Dependencies (other questions depend on this)

### 4. Research

- **Codebase:** Search relevant files
- **Documentation:** Check project docs
- **External:** Use WebSearch for industry practices
- **Knowledge base:** Query existing findings (if applicable)

#### Detect Diminishing Returns

| Signal | What It Means | Action |
|--------|--------------|--------|
| Sources agree | Confidence converging | Move on |
| New sources repeat findings | Saturation reached | Summarize |
| Confidence unchanged after 2-3 sources | Low gain | Next question |
| Finding contradictions | Uncertainty increasing | Dig deeper |

**Rule:** If confidence hasn't improved after 3 independent sources, move to next question.

### 5. Apply Project Lens

<!-- CUSTOMIZE: Add your project's principles/constraints -->

Consider:
- **Budget constraints** - Cost-effective approaches
- **Team size** - Maintainability
- **Tech stack** - Compatibility
- **Timeline** - Feasibility

### 6. Post Research Summary

```bash
linear issues comment --id $ARGUMENTS --body "## Research Summary

### Key Findings

1. **Finding 1** - Details with confidence level
2. **Finding 2** - Details with confidence level

### Files Affected
- \`path/to/file1\`
- \`path/to/file2\`

### Recommended Approach

[Describe the proposed solution]

### Open Questions

- Question 1?
- Question 2?

### Confidence Assessment
- Overall: High/Medium/Low
- Reasoning: [Why this confidence level]

---
Ready for implementation approval."
```

## Confidence Scoring

| Level | Score | Meaning |
|-------|-------|---------|
| High | >= 0.85 | Strong evidence, treat as constraint |
| Medium | 0.70-0.84 | Good evidence, follow unless reason not to |
| Low | < 0.70 | Preliminary, verify independently |
