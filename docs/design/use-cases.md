# Indigenous Craft Platform — Use Case Catalogue

## 1. Overview

The Indigenous Craft Platform is a web-based social and discovery platform for handmade and culturally rooted crafts. Users can discover crafts, share their own creations, follow other users, and interact with the community. Administrators manage platform data, review reports, and moderate content.

## 2. Actors

| Actor | Description |
|---|---|
| Guest | An unauthenticated visitor who can browse, search, filter, and view publicly available crafts and profiles. |
| Registered User | An authenticated user who can publish crafts, manage their profile, follow other users, comment, and submit reports and requests. |
| Admin | An administrator who manages platform data, reviews requests and reports, and moderates users and content. |

## 3. Authentication and Profile

| ID | Use Case | Actor | Description |
|---|---|---|---|
| UC-01 | Register Account | Guest | Creates an account by providing the required registration information. |
| UC-02 | Verify Email | Guest | Verifies the email address associated with a new account. |
| UC-03 | Log In | Guest | Authenticates using valid credentials. |
| UC-04 | Log Out | Registered User | Ends the current authenticated session. |
| UC-05 | Reset Password | Guest | Requests a password reset and sets a new password through the reset flow. |
| UC-06 | View Own Profile | Registered User | Views their own profile and account information. |
| UC-07 | Edit Profile | Registered User | Updates their profile information. |
| UC-08 | Delete Account | Registered User | Deletes their account while retaining their existing crafts and comments under a deleted-user identity. |
| UC-09 | View Public Profile | Guest, Registered User | Views another user's publicly available profile information and craft content. |
| UC-10 | View Followers / Following Lists | Registered User | Views the accounts that a user follows and the accounts following that user. Guests can see counts but not the lists. |

## 4. Craft Discovery

| ID | Use Case | Actor | Description |
|---|---|---|---|
| UC-11 | Browse Crafts | Guest, Registered User | Browses publicly available crafts. |
| UC-12 | Search Crafts | Guest, Registered User | Searches crafts using relevant text, including titles, descriptions, backstories, tags, categories, and property values. |
| UC-13 | Filter Crafts | Guest, Registered User | Filters crafts using categories and available craft properties. |
| UC-14 | View Craft Details | Guest, Registered User | Views a craft's details, images, story, properties, origin, and available comments. |
| UC-15 | View Crafts on Map | Guest, Registered User | Views crafts according to their recorded origin locations on a map. |

## 5. Craft Management

| ID | Use Case | Actor | Description |
|---|---|---|---|
| UC-16 | Create Craft | Registered User | Publishes a craft with its title, description, images, categories, properties, and other supported information. |
| UC-17 | Edit Own Craft | Registered User | Updates a craft created by the current user. |
| UC-18 | Deactivate Own Craft | Registered User | Deactivates their own craft without permanently deleting its record. |
| UC-19 | View Own Crafts | Registered User | Views the crafts associated with their own profile. |
| UC-20 | Create Category Tag | Registered User | Creates a category tag when an existing tag does not adequately describe a craft. The tag does not require prior Admin approval. |

## 6. Social Interaction and Reporting

| ID | Use Case | Actor | Description |
|---|---|---|---|
| UC-21 | Follow User | Registered User | Follows another user to receive their crafts in the timeline. |
| UC-22 | Unfollow User | Registered User | Stops following another user. |
| UC-23 | View Timeline | Registered User | Views a newest-first timeline of crafts published by followed users. |
| UC-24 | Comment on Craft | Registered User | Adds a comment to a craft. |
| UC-25 | Edit Own Comment | Registered User | Edits a comment created by the current user. |
| UC-26 | Report Craft | Registered User | Reports a craft by providing a reason and optional details. |
| UC-27 | Report Comment | Registered User | Reports a comment by providing a reason and optional details. |
| UC-28 | Report User | Registered User | Reports a user by providing a reason and optional details. |
| UC-29 | Report Category Tag | Registered User | Reports a category tag by providing a reason and optional details. |
| UC-30 | Request New Property | Registered User | Requests a new property definition that is not available on the platform. |
| UC-31 | Suggest New Property Value | Registered User | Suggests a new standard value for an existing property. A user can still enter an unsupported value as free text when creating a craft. |

## 7. Administration

### 7.1 Dashboard

| ID | Use Case | Actor | Description |
|---|---|---|---|
| UC-32 | View Admin Dashboard | Admin | Views platform metrics, including visitors, registered users, new crafts, active users, and report statistics. |

### 7.2 Category Tag Management

| ID | Use Case | Actor | Description |
|---|---|---|---|
| UC-33 | Create Category Tag (Admin) | Admin | Creates a category tag through the administration interface. |
| UC-34 | Edit Category Tag | Admin | Updates an existing category tag. |
| UC-35 | Deactivate Category Tag | Admin | Deactivates a category tag while preserving existing references where required. |

### 7.3 Property Management

| ID | Use Case | Actor | Description |
|---|---|---|---|
| UC-36 | Create Property | Admin | Defines a new property that can be used to describe crafts. |
| UC-37 | Edit Property | Admin | Updates an existing property definition. |
| UC-38 | Deactivate Property | Admin | Deactivates a property while preserving existing references where required. |

### 7.4 Property Value Management

| ID | Use Case | Actor | Description |
|---|---|---|---|
| UC-39 | Add Property Value | Admin | Adds a standard value to an existing property. |
| UC-40 | Edit Property Value | Admin | Updates an existing standard property value. |
| UC-41 | Deactivate Property Value | Admin | Deactivates a standard property value while preserving existing references where required. |

### 7.5 Request Management

| ID | Use Case | Actor | Description |
|---|---|---|---|
| UC-42 | Review Property Requests | Admin | Reviews requests for new properties and suggested standard property values. |
| UC-43 | Approve / Reject Property or Value Request | Admin | Approves or rejects a submitted property or property-value request. |

### 7.6 Report Management and Moderation

| ID | Use Case | Actor | Description |
|---|---|---|---|
| UC-44 | Review Reports | Admin | Reviews submitted reports and determines the appropriate outcome. |
| UC-45 | Take Action | Admin | Performs an optional moderation action, such as deactivating a craft or user, or deleting a comment. |
| UC-46 | Deactivate Craft (Admin) | Admin | Deactivates a craft that violates platform rules. |
| UC-47 | Deactivate User | Admin | Deactivates a user account that violates platform rules. |
| UC-48 | Delete Comment | Admin | Deletes a comment when moderation is required. |

## 8. Important Business Rules

- Guests can browse and search public crafts but cannot follow users, comment, or view follower/following account lists.
- Email verification is required for a newly registered account to use authenticated functionality.
- Users can edit their own comments, but cannot delete them. Admins can delete comments.
- Users can deactivate their own crafts. Admins can also deactivate crafts and user accounts.
- Deactivation is not permanent deletion. Existing records and necessary references are retained.
- User-created category tags do not require Admin approval. Admins can manage or deactivate category tags.
- New property definitions and suggested standard property values are separate request types.
- Free-text property values entered during craft creation remain searchable and filterable.
- Craft origin refers to the craft's origin location, not the current location of its creator.
- A report does not automatically hide the reported content. Admins review reports and decide whether moderation is needed.
- `Take Action` is optional during report review and is modeled as an `«extend»` relationship to `Review Reports`.

## 9. UML Relationship Notes

- Use solid association lines between actors and the use cases they can perform.
- Use `«include»` when the included behavior is required as part of the base use case.
- Use `«extend»` when additional behavior is optional or conditionally executed.
- Category tag, property, and property-value administration actions are represented as separate use cases rather than mandatory `«include»` relationships from a generic `Manage` use case.
- The `Guest` and `Registered User` actors share craft discovery use cases, while social interactions require a Registered User.
