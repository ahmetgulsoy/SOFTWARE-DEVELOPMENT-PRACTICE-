Software Requirements Specification

Indigenous Craft Platform

Version 1.2 | 18 October 2026
Project: Indigenous Craft Platform | Phase 1

# Document Control

| Item | Value |

| --- | --- |

| Version | 1.2 — clarified image uploads, color master data, dashboard metrics, reports and master-data removal |

| Status | Requirements baseline draft for review |

| Target delivery | 8 December 2026 |

| Development model | Single developer; phased delivery |

| Changes | Clarified direct image upload and object storage, color name + HEX, login-based active-user metrics, simple report statuses, and category/master-data removal rules. |

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

| Object storage | External dependency for storing uploaded image files; database stores image references and metadata. |

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

FR-11 Images: Registered users can upload images from their devices. Each craft supports up to 10 images in JPEG or PNG format. Each image must not exceed 5 MB. The system validates file type and size before accepting an upload.

FR-12 Multiple categories: A craft can belong to multiple categories.

FR-13 Reusable properties: A property is reusable across categories; Category and Property have a many-to-many relationship through a link such as CategoryProperty.

FR-14 Common properties: Common properties apply across categories without explicit association to each category.

FR-15 Dynamic properties: Category-specific properties appear when at least one associated category is selected. For multiple categories, show the union of applicable properties without duplicates.

FR-16 Admin property management: Admin creates/manages properties, sets Common or Category-specific scope and associates category-specific properties with one or more categories.

FR-17 Contextual help: Property values may have short descriptions; where present, the UI provides help such as an info icon or equivalent.

FR-18 Color: Color is represented by a name and HEX code and displayed with a visual swatch. The platform provides an initial set of basic colors. Users can submit a new color suggestion with a name and HEX code for Admin review. Color groups are not required for Phase 1.

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

FR-30 Moderation: Admin can deactivate accounts and crafts. Deactivation retains records. Reactivation may be supported by Admin; if implemented, it restores the item/account subject to normal rules.

FR-31 Deactivated accounts: Deactivated accounts cannot perform authenticated actions.

FR-32 Deactivated crafts: Deactivated crafts do not appear in normal public browsing, search or timelines.

FR-33 Comment moderation: Admin can delete comments.

FR-34 Dashboard: Admin views total registered users, active users for the selected period, new crafts by day/week/month, and pending/resolved/rejected report counts.

FR-35 Active-user metrics: An active user is a registered user who successfully logged in during the selected reporting period. Login events must be recorded so distinct users can be counted for daily, weekly and monthly periods. Visitor tracking is not required for Phase 1.

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

Each color has a name and HEX code and is shown with a visual swatch (e.g. Crimson / #DC143C). Admin provides an initial list of basic colors. Users may submit a new color suggestion by providing its name and HEX code; Admin approves or rejects the suggestion before it becomes available as shared master data. Color groups are not required in Phase 1. Crafts should reference a shared color record rather than duplicate color names and codes as free text.

## 4.4 Master-Data Removal

- If a category or other master-data record has not been used by any craft or dependent record, Admin may permanently delete it.
- If a category or master-data record is already referenced, Admin deactivates it instead of deleting it. Deactivated values are not offered for new selections or normal filters, while existing references are retained to preserve data integrity.
- Category merge functionality is out of scope for Phase 1.

## 4.5 Account and Content Retention

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

| NFR-08 Color usability | Do not rely on color alone; expose a name alongside the swatch. |
| NFR-09 Image handling | Validate image format and size; store image files outside the relational database and keep references/metadata in the database. |

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

| Object storage provider | Select an object storage provider and configure access/delivery during system design; the provider must support direct device uploads through the application flow. |

| Color catalog | Define the initial basic-color list and the Admin workflow for reviewing user color suggestions. |

| Traffic profile | Clarify expected visitors/concurrent users and define a repeatable performance test. |

| Dashboard metrics | Use successful login events to count distinct active users in daily, weekly and monthly periods; define the exact reporting window in implementation. Visitor tracking is out of scope. |

| Map/email providers | Select options appropriate to scope and deployment. |
| Performance test profile | Define representative data volume and concurrency for verifying the 3-second response-time target. |

| Reactivation behavior | Decide during design whether Admin reactivation will be included in Phase 1; deactivation and record retention are required. |

# 8. Validation and Acceptance

Before baseline approval, check each requirement for clarity, consistency, testability and traceability to design/implementation tasks. Review this SRS against the scope document, use cases, mockup decisions and project plan.
