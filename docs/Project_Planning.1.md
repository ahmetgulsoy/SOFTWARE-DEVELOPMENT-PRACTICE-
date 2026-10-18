Project Planning

Indigenous Craft Platform — Phase 1

Version 2.1 | 18 October 2026
Project: Indigenous Craft Platform | Phase 1

# 1. Planning Summary

This plan organizes the one-person development effort into nine milestones from project baseline through final delivery. Target submission is 8 December 2026. Final days are reserved for regression, demo and submission rather than new feature development.

| Item | Value |

| --- | --- |

| Approach | Incremental, milestone-based development |

| Team size | 1 developer |

| Target delivery | 8 December 2026 |

| Tracking | GitHub Issues, Milestones and labels |

| Source control | GitHub repository |

| Definition of done | Acceptance criteria met, behavior verified, changes committed and documentation updated where needed. |

# 2. Milestone Schedule

| Milestone | Start | Due date | Exit objective |

| --- | --- | --- | --- |

| M1 — Scope & Planning Baseline | 10 Oct 2026 | 16 Oct 2026 | Vision, stakeholders, requirements, scope, WBS, schedule, risks and GitHub setup. |

| M2 — Design Baseline | 17 Oct 2026 | 23 Oct 2026 | Use cases, architecture, ERD/data model, property model, search behavior and wireframes. |

| M3 — Foundation & Authentication | 24 Oct 2026 | 30 Oct 2026 | Application setup, database, registration, verification, login/logout and password reset. |

| M4 — Craft Management | 31 Oct 2026 | 6 Nov 2026 | Craft creation/editing, categories, dynamic properties, images, Backstory and origin. |

| M5 — Search & Social | 7 Nov 2026 | 13 Nov 2026 | Global search, category-aware filters, suggestions, follows, timeline and comments. |

| M6 — Admin & Feature Completion | 14 Nov 2026 | 20 Nov 2026 | Metadata management, requests, reports, moderation and dashboard. |

| M7 — Integration & Testing | 21 Nov 2026 | 27 Nov 2026 | Functional/integration tests, authorization, retention behavior and critical defects. |

| M8 — Deployment & Stabilization | 28 Nov 2026 | 4 Dec 2026 | Deployment, smoke tests, response-time check and release stabilization. |

| M9 — Final Delivery | 5 Dec 2026 | 8 Dec 2026 | Final regression, README, known limitations, demo and submission. |

Schedule note: As of 18 October 2026, the planned M1 end date has passed and M2 is in progress. This table preserves the original baseline. If work remains open, revise the schedule and record the adjustment rather than treating past dates as future commitments.

# 3. Work Breakdown Structure

| Work package | Included work |

| --- | --- |

| 1. Project initiation and planning | Vision/problem statement, stakeholders, elicitation, SRS, scope, schedule, WBS, risk register and GitHub setup. |

| 2. Requirements and system design | Use cases/flows, wireframes, architecture, ERD, auth design, Category–Property model, search/filter design and external-service decisions. |

| 3. Application foundation | Repository/build configuration, environment, database connectivity, initial schema/migrations and application skeleton. |

| 4. Identity and profiles | Registration, email verification, login/logout, password reset, profile management, authorization and account-deletion behavior. |

| 5. Craft management | Craft CRUD, categories, common/category-specific properties, master data, Backstory, tags, images, color and origin. |

| 6. Discovery and social | Global search, advanced filters/suggestions, filter logic, public profiles, follows, paginated timeline and comments. |

| 7. Administration and moderation | Property/value administration, requests, reports, account/craft deactivation, comment moderation and dashboard. |

| 8. Verification and quality | Functional/integration tests, authorization, retention, search/filter tests, defect fixing and performance checks. |

| 9. Deployment and delivery | Deployment config, smoke tests, README/setup, known limitations, final regression, demo and submission. |

# 4. Risk Register

| ID | Risk | Impact | Likelihood | Mitigation |

| --- | --- | --- | --- | --- |

| R1 | Scope growth from evolving mockup decisions | High | Medium | Keep Phase 1 boundaries explicit; record decisions in SRS; defer nonessential features. |

| R2 | Category/property model is hard to maintain | High | Medium | Use reusable Property definitions, many-to-many CategoryProperty associations and a separate common-property scope. |

| R3 | Color values become fragmented | Medium | Medium | Use visual swatches with HEX/RGB and controlled names/groups; finalize taxonomy before implementation. |

| R4 | Image/map/email integration takes longer than expected | Medium | Medium | Choose simple providers early; isolate provider-specific logic and document limitations. |

| R5 | Search/filter logic is inconsistent | High | Medium | Test case-insensitivity, same-property OR, cross-property AND and category-aware filters. |

| R6 | Performance target lacks load definition | Medium | High | Define expected traffic/concurrency and repeatable test profile before validation. |

| R7 | Single-developer schedule has little contingency | High | High | Prioritize core flows, review progress weekly and protect M9 for stabilization. |

| R8 | Dashboard metrics are ambiguous | Medium | Medium | Define visitor method, WAU/MAU windows and report-count rules before coding. |

| R9 | Account deletion confused with moderation deactivation | High | Medium | Test the two flows separately and document expected visibility and relationship behavior. |

# 5. Dependencies and Sequencing

- SRS and scope baseline inform system design.

- Category–Property model precedes dynamic craft forms and category-aware filters.

- Property/master-value model precedes searchable suggestions.

- Authentication and authorization precede user-owned crafts, follows, comments and admin workflows.

- Image, email and map approaches should be selected during design before integration.

- Test alongside implementation; M7 consolidates integration and regression.

# 6. GitHub Issue Management

- Use Milestones to group work by delivery phase and due date.

- Use Issues for requirements, documentation, design, implementation, testing, deployment and delivery—not only bugs/features.

- Each issue should have a clear title, description, acceptance criteria, labels, milestone and dependencies where relevant.

- Create focused M1/M2 issues first; detail later implementation issues after design decisions stabilize.

- Close issues only when acceptance criteria are met and the relevant deliverable/code is in the repository.

# 7. Definition of Done

- Acceptance criteria are met and behavior is verified.

- Relevant tests pass or known limitations are documented.

- Changes are committed to GitHub.

- Relevant documentation is updated.

- No unresolved critical defect blocks milestone exit.

# 8. Final Delivery Checklist

- Guest browsing/search and public profiles work.

- Registration, verification, login and password reset work.

- Craft management supports Backstory, categories, properties and images according to chosen design.

- Category-aware properties and filters follow documented OR/AND rules.

- Follow, timeline, comment and report workflows enforce authorization.

- Admin manages metadata, reviews reports, moderates content and views agreed metrics.

- User account deletion and Admin deactivation are tested separately.

- Deployment smoke tests and response-time checks are recorded.

- README, setup instructions, known limitations and demo are prepared.
