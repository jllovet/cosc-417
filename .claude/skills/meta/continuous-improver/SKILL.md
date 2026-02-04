---
name: continuous-improver
description: Meta-skill for iterative improvement of Claude skills, instructions, and workflows
user-invocable: true
allowed-tools: Read, Grep, Glob, Write, Edit, Bash(git:*), WebSearch
---

# Continuous Improver

Systematic enhancement of agent capabilities for compounding productivity gains.

## Mission

Create a self-improving system where each enhancement enables future enhancements.

```
Today's improvement -> Tomorrow's capability -> Next week's breakthrough
```

## Improvement Loop

```
OBSERVE -> ANALYZE -> PROPOSE -> APPROVE -> IMPLEMENT -> MEASURE
    ^                                                      |
    +------------------------------------------------------+
```

## When to Invoke

- After complex or frustrating sessions
- Weekly improvement ritual
- When observing repeated friction
- Before major capability additions
- Quarterly skill system reviews

---

## Phase 1: Observe

### Sources of Improvement Signals

| Source | What to Look For |
|--------|------------------|
| **Session friction** | Repeated clarifications, misunderstandings |
| **Skill gaps** | "I don't have a skill for X" moments |
| **Workflow bottlenecks** | Manual steps that could be automated |
| **Knowledge decay** | Outdated information |
| **Cross-skill friction** | Handoff failures between skills |
| **User feedback** | Explicit requests, frustrations |

### Observation Checklist

- [ ] What took longer than expected?
- [ ] What required multiple attempts?
- [ ] What knowledge was missing?
- [ ] What workflow was unclear?
- [ ] What collaboration failed?

---

## Phase 2: Analyze

### Root Cause Categories

| Category | Symptoms | Root Cause |
|----------|----------|------------|
| **Knowledge gap** | Wrong answers, outdated info | Missing/stale knowledge |
| **Skill gap** | No skill for domain | Missing skill definition |
| **Instruction ambiguity** | Inconsistent behavior | Unclear instructions |
| **Context limitation** | Lost context mid-task | Poor summarization |
| **Tool gap** | Manual workarounds | Missing automation |
| **Collaboration gap** | Skills don't compose | Missing handoff protocols |

### Analysis Framework

```markdown
Issue: [What went wrong]
Impact: [How it affects productivity]
Frequency: [How often it occurs]
Root cause: [Why it happens]
Leverage: [How fixing it enables other improvements]
```

### Entropy-Based Prioritization

For competing improvements, prioritize by uncertainty (highest entropy = highest ROI):

| Accuracy | Entropy | Priority |
|----------|---------|----------|
| 50% | 1.00 | Highest - coin flip |
| 70% | 0.88 | High |
| 90% | 0.47 | Low - already calibrated |

---

## Phase 3: Propose

### Proposal Template

```markdown
## Improvement Proposal: [Title]

### Problem
[Specific issue observed]

### Impact
- Frequency: [Daily/Weekly/Monthly]
- Time cost: [Minutes/hours per occurrence]
- Compound effect: [What else it enables]

### Proposed Solution
[Specific change]

### Files to Modify
- `path/to/file` - [Change description]

### Risks
- [What could go wrong]
- [How to mitigate]

### Success Metrics
- [How to measure improvement]

### Approval Needed
- [ ] Clarifying instructions (no)
- [ ] Adding capabilities (yes)
- [ ] Changing workflows (yes)
- [ ] Creating new skills (yes)
```

---

## Phase 4: Approve

### Approval Matrix

| Change Type | Self-Approve | Human Required |
|-------------|--------------|----------------|
| Fix typos | Yes | No |
| Clarify instructions | Yes | No |
| Add examples | Yes | No |
| Add capability to skill | No | Yes |
| Create new skill | No | Yes |
| Change workflow | No | Yes |
| Modify CLAUDE.md | No | Yes |

---

## Phase 5: Implement

### Guidelines

- Small, focused changes
- One improvement per commit
- Clear commit messages with rationale
- Test before finalizing
- Document changes

---

## Phase 6: Measure

### Metrics

| Metric | How to Measure |
|--------|----------------|
| Task completion time | Before/after comparison |
| Clarification requests | Fewer = better |
| Error rate | Fewer corrections needed |
| Skill utilization | Right skill invoked first |
| Workflow completion | End-to-end without friction |

---

## Improvement Categories

### Skill Improvements

| Type | Indicators | Actions |
|------|------------|---------|
| Coverage gap | Domain not addressed | Create new skill |
| Depth gap | Shallow guidance | Enhance existing |
| Clarity gap | Misapplied skill | Improve triggers |
| Collaboration gap | Skills don't connect | Add handoff protocols |

### Instruction Improvements

| File | Improvement Types |
|------|-------------------|
| `CLAUDE.md` | Rules, workflows, permissions |
| Skill files | Domain-specific guidance |
| Shared resources | Common patterns |

---

## Compound Improvement Patterns

### Pattern 1: Capability Stacking

```
Skill A improved -> Enables Skill B enhancement -> Unlocks new workflow
```

### Pattern 2: Friction Elimination

```
Manual step automated -> Time saved -> Applied to next automation
```

### Pattern 3: Knowledge Accumulation

```
Research captured -> Future research faster -> Expertise deepens
```

---

## Leverage Score

```
Leverage = (Impact x Frequency x Compound Effect) / Implementation Effort

High leverage: Fix once, benefit forever
Low leverage: Fix once, benefit once
```

---

## Output Format

```markdown
## Improvement Cycle Report: [Date]

### Observations
| Issue | Frequency | Impact |

### Analysis
| Issue | Root Cause | Leverage |

### Proposals
1. **[Improvement]**
   - Problem: [issue]
   - Solution: [change]
   - Effort: [Low/Med/High]
   - Leverage: [Low/Med/High]
   - Status: [Proposed/Approved/Implemented]

### Implemented This Cycle
1. [Change] - [Result]

### Metrics
| Metric | Before | After | Change |

### Next Cycle Focus
[Top priorities]
```

---

## Anti-Patterns

| Anti-Pattern | Why Harmful | Instead |
|--------------|-------------|---------|
| Over-engineering | Complexity > benefit | Minimum viable improvement |
| Premature optimization | Optimize wrong thing | Observe first |
| Scope creep | One becomes many | One change per cycle |
| Skipping measurement | Don't know if it worked | Always measure |
| Not documenting | Lost knowledge | Capture everything |

---

## Success Vision

A system where:
1. Every session teaches something
2. Improvements compound weekly
3. Manual work decreases over time
4. Agent capabilities grow continuously
5. Human approval focuses on high-leverage changes
6. The system gets better at getting better
