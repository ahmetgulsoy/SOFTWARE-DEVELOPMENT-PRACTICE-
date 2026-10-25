# Entity-Relationship (ER) Model Documentation

## 1. Overview

This document describes the database schema of the Indigenous Craft Platform. It lists the database tables, columns, data types, primary keys, foreign keys, unique constraints, and important relationships.

The schema supports user management, craft creation, category-based discovery, dynamic craft attributes, user interactions, and content moderation.

## 2. Database Tables

**Notation**

- **PK:** Primary Key
- **FK:** Foreign Key
- **UK:** Unique Key / Unique Constraint
- **NULL:** The column may contain a null value
- **NOT NULL:** The column must contain a value

### 2.1 User Management

#### USER

Stores user account information.

| Column | Data Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| first_name | STRING | |
| last_name | STRING | |
| email | STRING | UK |
| password_hash | STRING | |
| role | ENUM | USER, ADMIN |
| status | ENUM | PENDING, ACTIVE, DEACTIVATED, DELETED |
| created_at | DATETIME | |
| last_active_at | DATETIME | |
| deleted_at | DATETIME | NULL |

#### USER_TOKEN

Stores email verification and password reset tokens.

| Column | Data Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| user_id | BIGINT | FK → USER.id |
| type | ENUM | EMAIL_VERIFICATION, PASSWORD_RESET |
| token_hash | STRING | UK |
| expires_at | DATETIME | |
| used_at | DATETIME | NULL |
| created_at | DATETIME | |

#### FOLLOW

Represents the relationship between a follower and a followed user.

| Column | Data Type | Constraints |
|---|---|---|
| follower_id | BIGINT | PK, FK → USER.id |
| followed_id | BIGINT | PK, FK → USER.id |
| created_at | DATETIME | |

The composite primary key prevents the same user pair from being recorded more than once.

### 2.2 Craft Management

#### CRAFT

Stores craft information and its owner.

| Column | Data Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| owner_id | BIGINT | FK → USER.id |
| title | STRING | |
| description | TEXT | |
| backstory | TEXT | |
| latitude | DOUBLE | |
| longitude | DOUBLE | |
| status | ENUM | ACTIVE, DEACTIVATED, DELETED |
| status_changed_at | DATETIME | |
| created_at | DATETIME | |
| updated_at | DATETIME | |

#### CRAFT_IMAGE

Stores image metadata for craft items.

| Column | Data Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| craft_id | BIGINT | FK → CRAFT.id |
| storage_key | STRING | |
| content_type | STRING | |
| size_bytes | INT | |
| position | INT | |
| created_at | DATETIME | |

#### CATEGORY

Stores craft categories.

| Column | Data Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| name | STRING | |
| slug | STRING | UK |
| created_by | BIGINT | FK → USER.id |
| merged_into_id | BIGINT | FK → CATEGORY.id, NULL |
| deleted_at | DATETIME | NULL |
| created_at | DATETIME | |

The `merged_into_id` column supports linking a category to another category after a merge.

#### CRAFT_CATEGORY

Associates crafts with categories.

| Column | Data Type | Constraints |
|---|---|---|
| craft_id | BIGINT | PK, FK → CRAFT.id |
| category_id | BIGINT | PK, FK → CATEGORY.id |

The composite primary key prevents duplicate craft-category assignments.

### 2.3 Dynamic Attribute Management

#### ATTRIBUTE_DEFINITION

Defines the properties that can be used to describe crafts.

| Column | Data Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| name | STRING | UK |
| type | ENUM | TEXT, NUMBER, CHOICE, COLOR |
| multi_select | BOOLEAN | |
| allow_other | BOOLEAN | |
| is_common | BOOLEAN | |
| is_active | BOOLEAN | |
| sort_order | INT | |
| created_at | DATETIME | |

#### ATTRIBUTE_OPTION

Stores predefined options for attribute definitions.

| Column | Data Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| attribute_id | BIGINT | FK → ATTRIBUTE_DEFINITION.id |
| value | STRING | |
| description | STRING | |
| color_hex | STRING | |
| is_active | BOOLEAN | |
| created_at | DATETIME | |

#### CATEGORY_ATTRIBUTE

Associates categories with applicable attribute definitions.

| Column | Data Type | Constraints |
|---|---|---|
| category_id | BIGINT | PK, FK → CATEGORY.id |
| attribute_id | BIGINT | PK, FK → ATTRIBUTE_DEFINITION.id |

#### UNIT

Defines supported measurement units.

| Column | Data Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| name | STRING | |
| symbol | STRING | |
| unit_group | ENUM | LENGTH, WEIGHT |
| is_active | BOOLEAN | |

#### ATTRIBUTE_UNIT

Associates attribute definitions with supported measurement units.

| Column | Data Type | Constraints |
|---|---|---|
| attribute_id | BIGINT | PK, FK → ATTRIBUTE_DEFINITION.id |
| unit_id | BIGINT | PK, FK → UNIT.id |

#### CRAFT_ATTRIBUTE_VALUE

Stores values assigned to attribute definitions for individual crafts.

| Column | Data Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| craft_id | BIGINT | FK → CRAFT.id |
| attribute_id | BIGINT | FK → ATTRIBUTE_DEFINITION.id |
| value_text | STRING | |
| value_number | DECIMAL | |
| value_hex | STRING | |
| unit_id | BIGINT | FK → UNIT.id, NULL |

The value columns support different attribute types. The optional `unit_id` associates a value with its measurement unit when applicable.

#### CRAFT_ATTRIBUTE_OPTION

Associates crafts with selected predefined attribute options.

| Column | Data Type | Constraints |
|---|---|---|
| craft_id | BIGINT | PK, FK → CRAFT.id |
| option_id | BIGINT | PK, FK → ATTRIBUTE_OPTION.id |

### 2.4 User Interaction

#### COMMENT

Stores comments associated with crafts.

| Column | Data Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| craft_id | BIGINT | FK → CRAFT.id |
| author_id | BIGINT | FK → USER.id |
| content | STRING | Maximum 1024 characters |
| created_at | DATETIME | |
| edited_at | DATETIME | NULL |
| deleted_at | DATETIME | NULL |

### 2.5 Moderation and Administrative Requests

#### REPORT

Stores reports submitted by users.

| Column | Data Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| reporter_id | BIGINT | FK → USER.id |
| target_type | ENUM | CRAFT, COMMENT, USER, CATEGORY |
| target_id | BIGINT | Polymorphic target reference |
| reason | ENUM | SPAM, INAPPROPRIATE, MISLEADING, OTHER |
| description | STRING | |
| status | ENUM | PENDING, ACCEPTED, REJECTED |
| decided_by | BIGINT | FK → USER.id, NULL |
| decided_at | DATETIME | NULL |
| created_at | DATETIME | |

The combination of `target_type` and `target_id` identifies the reported entity. Since the target can belong to different tables, `target_id` does not use a conventional foreign key.

#### REQUEST

Stores user requests for new attribute properties or predefined options.

| Column | Data Type | Constraints |
|---|---|---|
| id | BIGINT | PK |
| requester_id | BIGINT | FK → USER.id |
| type | ENUM | NEW_PROPERTY, NEW_OPTION |
| attribute_id | BIGINT | FK → ATTRIBUTE_DEFINITION.id, NULL |
| source_value_id | BIGINT | FK → CRAFT_ATTRIBUTE_VALUE.id, NULL |
| requested_text | STRING | |
| description | STRING | |
| status | ENUM | OPEN, CLOSED |
| admin_comment | STRING | NULL |
| closed_by | BIGINT | FK → USER.id, NULL |
| closed_at | DATETIME | NULL |
| created_at | DATETIME | |

Optional references allow a request to be associated with an existing attribute or attribute value when applicable.

## 3. Key Relationships Summary

| Relationship | Cardinality |
|---|---|
| USER — CRAFT | One-to-many |
| USER — USER_TOKEN | One-to-many |
| USER — FOLLOW | One-to-many in each follower/followed role |
| CRAFT — CRAFT_IMAGE | One-to-many |
| CRAFT — CATEGORY | Many-to-many through CRAFT_CATEGORY |
| CATEGORY — ATTRIBUTE_DEFINITION | Many-to-many through CATEGORY_ATTRIBUTE |
| ATTRIBUTE_DEFINITION — ATTRIBUTE_OPTION | One-to-many |
| ATTRIBUTE_DEFINITION — UNIT | Many-to-many through ATTRIBUTE_UNIT |
| CRAFT — CRAFT_ATTRIBUTE_VALUE | One-to-many |
| ATTRIBUTE_DEFINITION — CRAFT_ATTRIBUTE_VALUE | One-to-many |
| CRAFT — ATTRIBUTE_OPTION | Many-to-many through CRAFT_ATTRIBUTE_OPTION |
| CRAFT — COMMENT | One-to-many |
| USER — COMMENT | One-to-many |
| USER — REPORT | One-to-many in reporter and decision-maker roles |
| USER — REQUEST | One-to-many in requester and closer roles |

## 4. Design Notes

- The dynamic attribute structure allows each craft category to use different properties without adding dedicated database columns for every property.
- Relationship tables represent many-to-many associations using composite primary keys.
- Nullable foreign keys represent optional relationships.
- `REPORT.target_type` and `REPORT.target_id` form a polymorphic reference rather than a conventional foreign key.
- Enumerated fields restrict values to the defined sets shown in the schema.
- The ER diagram should be used alongside this document to view the relationships graphically.