Software Requirements Specification

Indigenous Craft Platform

Version 1.1 | 18 October 2026
Project: Indigenous Craft Platform | Phase 1

# Document Control

| Item | Value |

| --- | --- |

| Version | 1.1 — consolidated after mockup discussion |

| Status | Requirements baseline draft for review |

| Target delivery | 8 December 2026 |

| Development model | Single developer; phased delivery |

| Changes | Backstory, color handling, many-to-many Category–Property, common properties and contextual help included. |

# 1. Introduction

## 1.1 Purpose

This document specifies Phase 1 requirements for a web-based social and discovery platform for handmade and culturally rooted crafts. It provides the baseline for design, implementation, verification and acceptance.

## 1.2 Product Scope

Visitors discover crafts and public profiles. Registered users publish crafts, follow creators, view a timeline of crafts from followed users and comment. Administrators manage shared metadata, requests, reports, accounts, craft visibility and basic platform metrics.

## 1.3 Definitions

| Term | Definition |

| --- | --- |

| Craft | A user-published entry describing a handmade or culturally rooted object. |

| Category | A classification assigned to a craft; a craft may belong to multiple categories. |

| Property | A structured characteristic such as Material, Color or Technique. |

| Common property | A property available independently of selected categories. |

| Category-specific property | A property available when at least one associated category is selected. |

| Master-data value | A reusable value managed in a shared catalog. |

| Free-text value | A user-entered value used when a suitable controlled value is unavailable. |

| Backstory | Optional narrative about the craft’s story, cultural context, origin story or making process. |

# 2. Actors and Context

| Actor | Capabilities |

| --- | --- |

| Guest | Browse/search crafts, view details and public profiles, see follower/following counts; cannot follow, comment or view follower/following account lists. |

| Registered User | Manage own profile/crafts; follow/unfollow; view newest-first timeline; comment/edit own comments; submit reports and metadata requests. |

| Administrator | Manage properties/master data, review requests/reports, deactivate/reactivate accounts and crafts where supported, delete comments and view dashboard. |

| Email service | External dependency for verification and password-reset email. |

| Map service | Optional external dependency for craft-origin location. |

| Image storage/service | External dependency if selected for image upload and delivery. |

# 3. Functional Requirements

FR-01 Registration: Guests register with name, surname, email and password.

FR-02 Verification: New accounts require email verification before full authenticated participation.

FR-03 Authentication: Users log in, log out and request password reset.

FR-04 Profile: Registered users view/edit their own profile.

FR-05 Account deletion: Crafts/comments remain under “Deleted user”; follow relationships are removed; re-registration does not relink old content.

FR-06 Browse: Guests and registered users browse visible crafts and view details.

FR-07 Public profiles: Public profiles and follower/following counts are visible; guests cannot see the corresponding account lists.

FR-08 Craft creation: Registered users create crafts with title, description, optional Backstory, images, tags, categories, properties and optional craft-origin location.

FR-09 Craft editing: Users edit their own craft entries.

FR-10 Own craft removal: Users remove their own craft entries through the user-facing flow; this differs from Admin deactivation.

FR-11 Images: Crafts support multiple images. If direct uploads are selected, initial assumption is up to 10 JPEG/PNG images per craft; reconcile with selected service.

FR-12 Multiple categories: A craft can belong to multiple categories.

FR-13 Reusable properties: A property is reusable across categories; Category and Property have a many-to-many relationship through a link such as CategoryProperty.

FR-14 Common properties: Common properties apply across categories without explicit association to each category.

FR-15 Dynamic properties: Category-specific properties appear when at least one associated category is selected. For multiple categories, show the union of applicable properties without duplicates.

FR-16 Admin property management: Admin creates/manages properties, sets Common or Category-specific scope and associates category-specific properties with one or more categories.

FR-17 Contextual help: Property values may have short descriptions; where present, the UI provides help such as an info icon or equivalent.

FR-18 Color: Color selection uses a visual swatch and stores a HEX/RGB code. A controlled name and/or color group supports filtering; final taxonomy is a design decision.

FR-19 Backstory: Optional free-text Backstory is separate from Description and captures narrative, cultural context, origin story or making story.

FR-20 Global search: Case-insensitive search covers title, description, Backstory, tags, category, structured property values and used free-text values.

FR-21 Advanced filters: Filters are category-aware: show common properties and properties applicable to selected categories.

FR-22 Filter suggestions: Suggestions include shared master values and free-text values already used by crafts; “silv” may suggest “Silver”. Do not suggest arbitrary unused values.

FR-23 Filter logic: Multiple values in one property group use OR; different property groups use AND; empty groups do not restrict results.

FR-24 Follow: Registered users follow/unfollow other users; self-follow is not allowed.

FR-25 Timeline: Registered users see a newest-first timeline of crafts from followed users, with pagination.

FR-26 Comments: Registered users comment and edit own comments. Users cannot delete comments; Admin can delete comments.

FR-27 Reporting: Registered users report crafts, comments and users with a reason and optional details.

FR-28 Report visibility: Reported crafts remain visible during review unless Admin acts. Automatic hiding by report-count threshold is out of scope.

FR-29 Metadata requests: Users suggest new master-data values or request a property; Admin reviews/manages requests.

FR-30 Moderation: Admin deactivates/reactivates accounts and crafts where supported. Moderation deactivation retains records.

FR-31 Deactivated accounts: Deactivated accounts cannot perform authenticated actions.

FR-32 Deactivated crafts: Deactivated crafts do not appear in normal public browsing, search or timelines.

FR-33 Comment moderation: Admin can delete comments.

FR-34 Dashboard: Admin views total visitors, registered users, new crafts by day/week/month, WAU/MAU and pending/resolved reports.

FR-35 Metric definitions: Visitor-counting method, active-user windows and report-counting rules are defined before implementation.

FR-36 Craft origin: A craft may have an origin location; this is not the user’s current location.

FR-37 Pagination: Search results and timeline support pagination.

# 4. Domain and Data Rules

## 4.1 Category–Property Model

Use a many-to-many relationship between Category and Property. One craft can have multiple categories; one category can use multiple properties; one category-specific property can be associated with multiple categories. Common properties are category-independent and need no per-category associations. When multiple categories are selected, show the union of common and applicable category-specific properties, without duplicates.

## 4.2 Values and Master Data

- Property definitions and property values are separate concepts.

- If the UI offers “Other”, store the actual entered value (e.g. “Copper”), not the literal word “Other”.

- Free-text values already used by crafts remain searchable and can appear in suggestions.

- Users may recommend new values; Admin controls the shared catalog.

- Users cannot create new property types directly; they submit a request for Admin review.

## 4.3 Color Strategy

Proposed approach: visual swatch + HEX/RGB code + controlled color name/group (e.g. Crimson / #DC143C / Red). This supports precise representation and broader group-based filtering. Canonical names, groups and matching rules must be finalized during design.

## 4.4 Account and Content Retention

- Admin deactivation is a moderation action that retains records and may be reversible.

- User-initiated account deletion is distinct: crafts/comments remain under “Deleted user”, follow relationships are removed and re-registration does not relink historical content.

- Deactivated accounts cannot perform authenticated actions; deactivated crafts are excluded from public discovery.

# 5. Non-Functional Requirements

| ID | Requirement |

| --- | --- |

| NFR-01 Performance | Craft detail, search and timeline should respond within 3 seconds under a documented test profile; define traffic/concurrency before testing. |

| NFR-02 Usability | Show relevant category-specific properties plus common properties; provide contextual help where descriptions exist. |

| NFR-03 Search consistency | Apply case-insensitive search and documented OR/AND filter rules consistently. |

| NFR-04 Security | Enforce authentication, role authorization and ownership checks server-side. |

| NFR-05 Data integrity | Keep category/property links, master values, ownership and moderation status consistent. |

| NFR-06 Maintainability | Admin-managed data should allow category/property associations to change without code changes for each association. |

| NFR-07 Logging | Minimal operational/error logging is required; detailed audit logging is not required for Phase 1. |

| NFR-08 Color usability | Do not rely on color alone; expose a name or code alongside the swatch. |

# 6. Out of Scope

- Personalized or machine-learning recommendations.

- Nested comment replies.

- Automatic report-threshold-based hiding.

- Full blog/CMS for technique education; short contextual descriptions are sufficient.

- Unrestricted user-created property types.

- Advanced semantic search or ML-based ranking.

# 7. Open Design Decisions

| Decision | Next action |

| --- | --- |

| Image handling | Choose object storage plus database references or an external image service; confirm image limits. |

| Color taxonomy | Confirm canonical names/groups, HEX/RGB storage and group-filter behavior. |

| Traffic profile | Clarify expected visitors/concurrent users and define a repeatable performance test. |

| Dashboard metrics | Define visitor method, WAU/MAU windows and report-status counting. |

| Map/email providers | Select options appropriate to scope and deployment. |

| Reactivation behavior | Confirm whether Admin can reactivate deactivated accounts/crafts and define resulting behavior. |

# 8. Validation and Acceptance

Before baseline approval, check each requirement for clarity, consistency, testability and traceability to design/implementation tasks. Review this SRS against the scope document, use cases, mockup decisions and project plan.
