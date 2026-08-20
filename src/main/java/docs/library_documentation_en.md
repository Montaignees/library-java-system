# LIBRARY MANAGEMENT SYSTEM

## PROJECT DOCUMENTATION

| Field | Information |
|---|---|
| **Author(s)** | Lucas De Montaigne Teixeira Brito |
| **Course** | Software Engineering |
| **Institution** | Faculdade Senai - Fatesg |
| **Version** | 1.0 |
| **Date** | 20/08/2026 |
| **Status** | In Development |

---

# 1. Introduction

## 1.1 Objective

The objective of the library management software is to organize and facilitate the management of data related to readers, books, loans, returns, and fines. The system aims to centralize this information, making library operations more efficient and reducing possible errors in data management.

## 1.2 Scope

The system will manage the main operations of a library, including the registration and management of readers and books, loan registration, return control, and fine calculation.

The initial scope does not include features such as online payments, integration with external systems, inter-library loans, or employee management beyond functions directly related to the system.

## 1.3 Target Audience

The system will primarily be intended for readers and librarians.

For readers, the system will allow them to track their loans, returns, and possible fines. For librarians, the system will facilitate the management of books, readers, loans, returns, and fines while keeping these operations recorded and organized.

---

# 2. Functional Requirements

## RF-001 — Book Registration

**Description:**  
The system must allow the librarian to register new books, storing the information required for their identification and management.

**Priority:** High

**Acceptance Criteria:**

- Allow the librarian to enter the book data.
- Validate the required registration data.
- Register the book in the system.
- Inform the librarian whether the registration was successful.

## RF-002 — Book Search

**Description:**  
The system must allow the librarian to search for registered books by book title.

**Priority:** High

**Acceptance Criteria:**

- Allow the librarian to enter the book title for the search.
- Display the information of the found book.
- Inform the librarian when no matching book is found.

## RF-003 — Reader Registration

**Description:**  
The system must allow the librarian to register new readers, storing the information required for their identification and management.

**Priority:** High

**Acceptance Criteria:**

- Allow the librarian to enter the reader data.
- Validate the required registration data.
- Register the reader in the system.
- Inform the librarian whether the registration was successful.

## RF-004 — Reader Search

**Description:**  
The system must allow the librarian to search for registered readers by reader name.

**Priority:** High

**Acceptance Criteria:**

- Allow the librarian to enter the reader's name for the search.
- Display the information of the found reader.
- Inform the librarian when no matching reader is found.

## RF-005 — Book Loan

**Description:**  
The system must allow the librarian to register a book loan for a reader, updating the book's availability and recording the loan information.

**Priority:** High

**Acceptance Criteria:**

- Allow the librarian to select a registered reader.
- Allow the librarian to select a registered book.
- Check whether the book is available for loan.
- Register the loan of the book to the reader.
- Change the book status to unavailable.
- Inform the librarian when the loan is successfully registered.
- Prevent unavailable books from being loaned.

## RF-006 — Book Return

**Description:**  
The system must allow the librarian to register the return of a borrowed book, updating its availability and the information related to the loan.

**Priority:** High

**Acceptance Criteria:**

- Allow the librarian to select the reader responsible for the loan.
- Allow the librarian to select the book being returned.
- Check whether the book has an active loan.
- Register the book return.
- Change the book status to available.
- Update the information related to the loan.
- Inform the librarian when the return is successfully registered.

---

# 3. Business Rules

## RN-001 — Return Deadline

**Description:**  
Each borrowed book must be returned by the reader within a maximum period of 28 days from the loan date.

## RN-002 — Late Fine

**Description:**  
In case of a late return, a fine of **R$ 1.00 per day and per item** will be applied.

---

# 4. Non-Functional Requirements

## RNF-001 — Terminal Interaction

**Description:**  
The system must allow the librarian to interact with the system through the terminal.

**Priority:** High

**Acceptance Criteria:**

- The system must display the available options through the terminal.
- The librarian must be able to select operations through the terminal.

## RNF-002 — Response Time

**Description:**  
The system must respond to operations performed by the librarian within 1 second.

**Priority:** High

**Acceptance Criteria:**

- The system must provide the operation result after processing with a response time of less than 1 second.

### Non-Functional Requirements Summary

| ID | Category | Description | Priority |
|---|---|---|---|
| RNF-001 | Interaction | System interaction through the terminal. | High |
| RNF-002 | Performance | Response time of less than 1 second. | High |

---

# 5. Main Entities

| Entity | Responsibility |
|---|---|
| **Book** | Stores and manages book information and availability. |
| **Reader** | Stores reader information, loans, and outstanding debts. |
| **Loan** | Records and manages the loan and return of a book. |
| **Fine** | Records and manages amounts generated by late returns. |

---

# 6. Class Diagram

> **[Insert the class diagram here.]**

**Figure 1 — System class diagram.**

---

# 7. Change History

| Version | Date | Change | Author |
|---|---|---|---|
| 0.1 | 20/08/2026 | Initial document. | Lucas De Montaigne |

---

# 8. Notes

The project is still under development. New features may be added in the future, such as information persistence using JSON files, allowing book, reader, and loan data to be maintained even after the system is closed.
