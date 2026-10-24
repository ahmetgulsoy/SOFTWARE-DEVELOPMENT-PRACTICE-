# Project Scope Definition

## Indigenous Craft Platform — Phase 1

**Version:** 2.1 | **Date:** 18 October 2026  
**Project:** Indigenous Craft Platform | **Phase:** 1

---

## 1. Project Overview

The Indigenous Craft Platform is a web-based social and discovery environment for handmade and culturally rooted crafts. It enables creators to document crafts, share their stories, discover related work, and connect with other creators.

## 2. Purpose and Objectives

- Provide a searchable catalogue of crafts with images, descriptions, optional Backstory, tags, categories, structured properties, and origin information.
- Enable registered users to publish and maintain crafts, follow creators, view a timeline, and comment.
- Support reusable property definitions, category-specific properties, and common properties available across categories.
- Provide Admin tools for metadata, requests, moderation, reports, and basic metrics.
- Deliver a demonstrable Phase 1 web application by 8 December 2026.

## 3. In-Scope Features

### 3.1 Accounts and Profiles

- Registration, email verification, login/logout, and password reset.
- Profile view/edit and user-initiated account deletion with retained-content behavior.
- Guest browsing and public profiles; show follower/following counts but not account lists to guests.

### 3.2 Craft Management

- Create, view, edit, and remove own craft entries.
- Title, description, optional Backstory, tags, images, multiple categories, structured properties, and craft-origin location.
- Category-aware dynamic properties; common properties available regardless of category.
- Visual color selection with HEX/RGB and a controlled name/group approach, subject to final design.
- Short contextual explanations for technical property values where descriptions exist.

### 3.3 Discovery and Social

- Case-insensitive global search across title, description, Backstory, tags, categories, and property values.
- Advanced filters with suggestions from master data and free-text values already used by crafts.
- OR within a property group; AND across different property groups.
- Follow/unfollow, newest-first paginated timeline, comments, and editing of own comments.
- Reports for crafts, comments, and users.

### 3.4 Administration

- Manage categories, properties, Common/Category-specific scope, and master-data values.
- Review requests for new properties and values.
- Review/resolve reports; moderate accounts and crafts through deactivation; reactivate if included in the final design.
- Delete comments and view agreed basic platform metrics.

## 4. Out of Scope

- Personalized recommendations or machine-learning ranking.
- Nested comment replies and discussion threads.
- Automatic report-threshold-based hiding.
- Full blog/CMS for technique education.
- Unrestricted user-created property types.
- Marketplace payments, shipping, and order management.
- Native mobile applications.
- Production-grade high-availability or large-scale distributed infrastructure beyond course needs.

## 5. Deliverables

| Deliverable | Description |
|---|---|
| Requirements baseline | SRS with Phase 1 functional/non-functional requirements and open design decisions. |
| Project management baseline | Scope definition, WBS, schedule, milestones, and risk register. |
| System design package | Use cases/flows, architecture, ERD/data model, UI wireframes, and design decisions. |
| Working application | Phase 1 web app covering authentication, craft management, search/filtering, social features, and administration. |
| Verification package | Test cases/results, functional checks, response-time check against a documented profile, and defect list. |
| Deployment and documentation | Demo deployment where feasible, README/setup guide, known limitations, and final presentation. |

## 6. Stakeholders and User Groups

| Stakeholder | Interest / Responsibility |
|---|---|
| Developer | Requirements, design, implementation, testing, deployment, and documentation. |
| Course instructor | Reviews progress, requirements, planning, and final deliverables. |
| Guest visitors | Discover crafts and public creator profiles. |
| Registered users | Publish crafts, maintain profiles, follow users, comment, and report. |
| Administrator | Manage metadata, requests, reports, accounts, crafts, and dashboard. |
| External providers | Email, map, and image storage/service dependencies if selected. |

## 7. Constraints and Assumptions

- One-person project with a target deadline of 8 December 2026.
- Development is organized into nine milestones.
- Pagination is preferred over infinite scrolling.
- Craft origin refers to the craft, not the user's current location.
- Admin deactivation retains records; user-initiated account deletion preserves crafts/comments under "Deleted user" and removes follow relationships.
- Response-time target is 3 seconds for craft detail, search, and timeline under a documented profile; the load profile needs definition.
- Image, map, and email providers, as well as the exact color taxonomy, are design decisions.

## 8. Scope Acceptance Criteria

- Every in-scope feature is represented in the SRS and traceable to planned work.
- Out-of-scope features are explicitly excluded from Phase 1 commitments.
- Scope and planning documents use the same milestone schedule and 8 December 2026 deadline.
- The application demonstrates guest discovery, authenticated craft management, category-aware filtering, social interaction, and essential admin workflows.
- Known limitations and deferred design decisions are documented before submission.
