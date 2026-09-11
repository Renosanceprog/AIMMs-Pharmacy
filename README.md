# Database Schema Reference 

## Overview

```jsx
data/
├── accounts/
│   ├── adminDB.csv              ✅ documented
│   ├── doctorDB.csv             ✅ documented
│   ├── pharmacistDB.csv         ✅ documented
│   └── customerDB.csv           ✅ documented
│
├── inventory/
│   ├── itemsDB.csv              ✅ documented
│   └── batchDB.csv              ✅ documented
│
├── prescriptions/
│   ├── prescriptionsDB.csv      ✅ documented
│   └── prescriptionItemsDB.csv  ✅ documented
│
└── logs/
    ├── inventoryLogs.csv        ✅ documented
    ├── prescLogs.csv            ✅ documented
    ├── batchLogs.csv            ✅ documented
    ├── transactionLogs.csv      ✅ documented
    └── balanceLogs.csv          ✅ documented
```

**All 13 schemas are now fully documented.** 🎉

Decided **not** to build:

- `restockCatalogDB.csv`
- a prescription upload/files directory

---

# Accounts

## 1. adminDB.csv

**Schema:** `adminID,username,password,fullName,status,dateCreated`

| Field | Description |
| --- | --- |
| `adminID` | Unique identifier assigned to the administrator. Example: `ADM-001` |
| `username` | Unique username used to log in |
| `password` | Admin's stored password/credential. Should later use password hashing, not plaintext |
| `fullName` | Administrator's complete name |
| `status` | Current status of the admin account |
| `dateCreated` | Date the admin account was created |

**Enum `status`:** `ACTIVE`, `INACTIVE`

For MVP, manually created admins default to `ACTIVE`. `INACTIVE` reserved for future account-disabling support.

**Example:**

```
ADM-001,admin,password123,Juan Dela Cruz,ACTIVE,2026-09-01
```

> Password shown is illustrative only — real implementation must not store plaintext passwords.
> 

---

## 2. doctorDB.csv

**Schema:** `doctorID,username,password,fullName,status,dateRegistered,dateApproved`

| Field | Description |
| --- | --- |
| `doctorID` | Unique identifier for the Doctor |
| `username` | Unique login username |
| `password` | Doctor's stored password/credential |
| `fullName` | Doctor's complete name |
| `status` | Registration/account approval state |
| `dateRegistered` | Date the Doctor submitted registration |
| `dateApproved` | Date the Admin approved the Doctor |

**Enum `status`:** `PENDING`, `ACTIVE`, `REJECTED`

- `PENDING` — registration submitted, awaiting Admin decision
- `ACTIVE` — Admin approved; account can log in
- `REJECTED` — Admin rejected the registration

`dateApproved` stays blank while `PENDING` or `REJECTED`.

**Examples:**

```
DOC-001,drjuan,password123,Juan Dela Cruz,PENDING,2026-09-10,
DOC-002,drmaria,password456,Maria Santos,ACTIVE,2026-09-08,2026-09-09
DOC-003,drpedro,password789,Pedro Reyes,REJECTED,2026-09-08,
```

---

## 3. pharmacistDB.csv

**Schema:** `pharmacistID,username,password,fullName,status,dateRegistered,dateApproved`

| Field | Description |
| --- | --- |
| `pharmacistID` | Unique identifier for the Pharmacist |
| `username` | Unique login username |
| `password` | Pharmacist's stored password/credential |
| `fullName` | Pharmacist's complete name |
| `status` | Registration/account approval state |
| `dateRegistered` | Date the Pharmacist submitted registration |
| `dateApproved` | Date the Admin approved the Pharmacist |

**Enum `status`:** `PENDING`, `ACTIVE`, `REJECTED` — same lifecycle as Doctor.

```
Registration → PENDING → REJECTED or ACTIVE
```

**Examples:**

```
PHA-001,pharmacyjuan,password123,Juan Dela Cruz,PENDING,2026-09-10,
PHA-002,pharmacymaria,password456,Maria Santos,ACTIVE,2026-09-08,2026-09-09
PHA-003,pharmacypedro,password789,Pedro Reyes,REJECTED,2026-09-08,
```

---

## 4. customerDB.csv

**Schema:** `customerID,username,password,fullName,balance,status,dateRegistered`

| Field | Description |
| --- | --- |
| `customerID` | Unique identifier for the Customer |
| `username` | Unique login username |
| `password` | Customer's stored password/credential |
| `fullName` | Customer's complete name |
| `balance` | Current internal account credit available for purchases |
| `status` | Current customer account status |
| `dateRegistered` | Date the customer registered |

**Enum `status`:** `ACTIVE`, `INACTIVE`

Unlike Doctor/Pharmacist, customers are **automatically activated** after registration.

**Examples:**

```
CUS-001,juan123,password123,Juan Dela Cruz,1000.00,ACTIVE,2026-09-10
CUS-002,maria456,password456,Maria Santos,500.00,ACTIVE,2026-09-11
```

> **Important:** `customerDB.balance` is the *current state*. `balanceLogs.csv` is the *history* of how it changed. We don't store every top-up/purchase inside `customerDB` itself.
> 

---

## Account registration lifecycles

```
ADMIN        — Manual creation → ACTIVE
CUSTOMER     — Self-registration → ACTIVE
DOCTOR       — Self-registration → PENDING → REJECTED / ACTIVE
PHARMACIST   — Self-registration → PENDING → REJECTED / ACTIVE
```

---

# Inventory

## 5. itemsDB.csv

**Schema:** `itemID,itemName,category,price,prescriptionRequired,status`

| Field | Description |
| --- | --- |
| `itemID` | Unique identifier for the product. Example: `ITM-001` |
| `itemName` | Name of the medicine/product |
| `category` | Product classification/category |
| `price` | Current selling price per unit |
| `prescriptionRequired` | Whether a Doctor-approved prescription is required before purchase |
| `status` | Whether the product is currently enabled in AIMMs |

**`category`** — no strict enum locked yet. Kept flexible; expected values include `TABLET`, `CAPSULE`, `SYRUP`, `CREAM`, `OINTMENT`, `DROPS`, `INHALER`, etc. MVP uses whatever set we establish when entering products, rather than hard-coding a full medical classification system.

**`prescriptionRequired`:** `true` / `false` — drives the app's `All` / `OTC` / `Prescription` views.

**Enum `status`:** `ACTIVE`, `INACTIVE`

> **Critical distinction:** `ACTIVE` does **not** mean the item has stock. It means *the product is enabled/recognized by AIMMs and may be stocked/sold if valid inventory exists.* `ITM-001 → ACTIVE` with `quantity = 0` is completely valid — the product is simply out of stock. `INACTIVE` means Admin has disabled/pulled the product entirely.
> 

**Examples:**

```
ITM-001,Paracetamol 500mg,Tablet,5.00,false,ACTIVE       (OTC)
ITM-002,Amoxicillin 500mg,Capsule,12.50,true,ACTIVE       (Prescription)
ITM-003,Example Medicine,Tablet,25.00,false,INACTIVE      (Disabled)
```

---

## 6. batchDB.csv

**Schema:** `batchID,itemID,quantity,expirationDate,dateReceived,status`

| Field | Description |
| --- | --- |
| `batchID` | Unique identifier for a physical inventory batch |
| `itemID` | Product that this batch belongs to |
| `quantity` | Current number of usable units remaining in this batch |
| `expirationDate` | Date the physical batch expires |
| `dateReceived` | Date the batch was added/received into inventory |
| `status` | Current state of the physical batch |

**Enum `status`:** `ACTIVE`, `DEPLETED`, `EXPIRED`, `PULLED`

- **`ACTIVE`** — usable; expiration not passed and `quantity > 0`
- **`DEPLETED`** — `quantity` reached 0. Row is **not deleted**
- **`EXPIRED`** — past expiration date; must not be used for sales even if `quantity > 0`
- **`PULLED`** — Admin removed the batch (e.g. damaged packaging); record stays for historical purposes

**Example flow — Paracetamol shipment:**

```
BAT-001,ITM-001,100,2027-06-30,2026-09-11,ACTIVE     (received 100)
BAT-001,ITM-001,80,2027-06-30,2026-09-11,ACTIVE      (after 20 sold)
BAT-001,ITM-001,0,2027-06-30,2026-09-11,DEPLETED     (row stays)
BAT-002,ITM-001,100,2028-01-31,2027-01-05,ACTIVE     (new shipment = new batch, BAT-001 is never reused)
```

**Relationship:** `itemsDB` (permanent product catalog) → `itemID` → `batchDB` (physical inventory, one item can have many batches)

```
ITM-001 Paracetamol 500mg
  ├── BAT-001 → 0 units   → DEPLETED
  ├── BAT-002 → 80 units  → ACTIVE
  └── BAT-003 → 50 units  → ACTIVE
```

**Selling availability rule:**

```
Available for sale  ⟺  item.status == ACTIVE
                    AND a batch exists with status == ACTIVE
                    AND batch.quantity > 0
                    AND batch is not expired
```

| Scenario | Meaning |
| --- | --- |
| ACTIVE item + 0 stock | Out of stock |
| INACTIVE item + 100 stock | Product disabled in AIMMs |
| ACTIVE item + ACTIVE batch + qty > 0 | Available for sale |

> Don't confuse "out of stock" with "delete the item" — they're separate concepts.
> 

---

# Prescriptions

## 7. prescriptionsDB.csv

**Schema:** `prescriptionID,customerID,doctorID,status,dateRequested,dateApproved,validUntil`

| Field | Description |
| --- | --- |
| `prescriptionID` | Unique identifier for the prescription/authorization |
| `customerID` | Customer who requested the prescription |
| `doctorID` | Doctor who reviewed the request |
| `status` | Current state of the prescription |
| `dateRequested` | Date/time the customer submitted the request |
| `dateApproved` | Date/time the Doctor approved it |
| `validUntil` | Date until which the approved prescription may be used |

**Enum `status`:**

| Status | Meaning |
| --- | --- |
| `PENDING` | Waiting for Doctor review |
| `APPROVED` | Doctor approved; currently usable |
| `REJECTED` | Doctor rejected the request |
| `EXPIRED` | `validUntil` has passed |
| `DISPENSED` | Successfully fulfilled through a completed transaction |
| `CANCELLED` | Cancelled and can no longer be used |

**Examples:**

```
RX-001,CUS-001,,PENDING,2026-09-11 09:00,,                                  (pending)
RX-002,CUS-001,DOC-001,APPROVED,2026-09-11 09:00,2026-09-11 10:30,2026-10-11 (approved)
RX-003,CUS-002,DOC-001,REJECTED,2026-09-11 11:00,,                          (rejected)
RX-004,CUS-003,DOC-002,DISPENSED,2026-09-10 09:00,2026-09-10 10:00,2026-10-10 (dispensed)
```

> This table represents the **authorization itself**, not the medicines it authorizes — those live in `prescriptionItemsDB.csv`.
> 

---

## 8. prescriptionItemsDB.csv

**Schema:** `prescriptionItemID,prescriptionID,itemID,quantity`

| Field | Description |
| --- | --- |
| `prescriptionItemID` | Unique identifier for this prescription-item record |
| `prescriptionID` | Prescription this item belongs to |
| `itemID` | Product authorized by the Doctor |
| `quantity` | Maximum/exact quantity authorized for this item |

**Example** — Doctor approves 10 units of `ITM-002` and 20 units of `ITM-005` under `RX-002`:

```
RXI-001,RX-002,ITM-002,10
RXI-002,RX-002,ITM-005,20
```

**Relationship:**

```
prescriptionsDB → prescriptionID → prescriptionItemsDB → itemID → itemsDB
```

Same parent/child pattern as transactions: Prescription → Prescription Items → Item Catalog.

---

### Critical prescription rule

A prescription does **not** unlock all prescription-required products generally — it authorizes only the **specific items and quantities** listed in `prescriptionItemsDB`. A customer cannot use `RX-002` (authorized for `ITM-002` and `ITM-005`) to buy `ITM-009`, even if `ITM-009` also requires a prescription. The pharmacist must verify the requested purchase matches the approved prescription exactly.

**Exact fulfillment only (MVP):**

| Approved qty | Attempted purchase | Result |
| --- | --- | --- |
| 10 | 10 | ✅ allowed |
| 10 | 5 | ❌ not allowed |
| 10 | 15 | ❌ not allowed |

If any prescribed item can't be fully fulfilled due to insufficient valid inventory, the **entire transaction is blocked** — no partial dispensing.

---

# Logs

## 9. inventoryLogs.csv

**Schema:** `logID,batchID,itemID,actorID,actorRole,action,quantity,dateTime,details`

| Field | Description |
| --- | --- |
| `logID` | Unique ID for this inventory event |
| `batchID` | The specific physical batch affected |
| `itemID` | The product associated with that batch |
| `actorID` | ID of the person/system that caused the change |
| `actorRole` | Role of the actor |
| `action` | What happened to the inventory |
| `quantity` | Number of units affected |
| `dateTime` | When the event happened |
| `details` | Optional human-readable explanation |

**Enum `action`:** `RESTOCK`, `SALE`, `ADJUSTMENT`

**Examples:**

```
LOG-001,BAT-001,ITM-001,ADM-001,ADMIN,RESTOCK,100,2026-09-11 10:00,Received 100 units into inventory
LOG-002,BAT-001,ITM-001,PHA-001,PHARMACIST,SALE,3,2026-09-11 14:30,Sold through TXN-001
LOG-003,BAT-001,ITM-001,ADM-001,ADMIN,ADJUSTMENT,-2,2026-09-11 15:00,Corrected inventory count after physical stock check
```

---

## 10. prescLogs.csv

**Schema:** `logID,prescriptionID,actorID,actorRole,action,dateTime,details`

| Field | Description |
| --- | --- |
| `logID` | Unique ID for this prescription event |
| `prescriptionID` | The prescription this event belongs to |
| `actorID` | ID of the person/system that caused the change |
| `actorRole` | Role of the actor |
| `action` | What happened to the prescription |
| `dateTime` | When the event happened |
| `details` | Optional human-readable explanation |

Tracks prescription lifecycle events (e.g. request submitted, approved, rejected, dispensed, cancelled) — the audit trail behind the `status` transitions in `prescriptionsDB.csv`.

---

## 11. batchLogs.csv

**Schema:** `logID,batchID,itemID,actorID,actorRole,action,dateTime,details`

| Field | Description |
| --- | --- |
| `logID` | Unique ID for this batch event |
| `batchID` | Batch affected by the action |
| `itemID` | Product associated with the batch |
| `actorID` | Person/system that performed the action |
| `actorRole` | Role of the actor |
| `action` | What happened to the batch |
| `dateTime` | When it happened |
| `details` | Optional human-readable explanation |

**Enum `action`:** `CREATE`, `PULL`, `REACTIVATE`, `EXPIRE`

- **`CREATE`** — Admin adds a new physical batch
- **`PULL`** — Admin removes/deactivates a batch from usable inventory (e.g. damaged packaging). The batch row stays in `batchDB.csv`; only its status changes
- **`REACTIVATE`** — Admin makes a previously pulled batch usable again (only valid if the batch is still genuinely usable)
- **`EXPIRE`** — the batch reaches its expiration date. `actorID`/`actorRole` can be `SYSTEM` here, since no one has to manually trigger it

**Examples:**

```
LOG-001,BAT-001,ITM-001,ADM-001,ADMIN,CREATE,2026-09-11 10:00,Added new batch with 100 units
LOG-002,BAT-001,ITM-001,ADM-001,ADMIN,PULL,2026-09-11 12:00,Batch pulled due to damaged packaging
LOG-003,BAT-001,ITM-001,ADM-001,ADMIN,REACTIVATE,2026-09-11 13:00,Batch cleared for inventory use
LOG-004,BAT-001,ITM-001,SYSTEM,SYSTEM,EXPIRE,2026-10-01 00:00,Batch reached expiration date
```

> **`batchLogs` vs `inventoryLogs`:** these look similar but record different things. `inventoryLogs` tracks *quantity movement* (`RESTOCK +100`, `SALE -3`). `batchLogs` tracks *batch lifecycle* (`CREATE`, `PULL`, `REACTIVATE`, `EXPIRE`). There's no `quantity` column here on purpose — that's `inventoryLogs`' job, and keeping them separate avoids duplicate logging of the same event.
> 

---

## 12. transactionLogs.csv

**Schema:** `logID,transactionID,customerID,actorID,actorRole,action,dateTime,details`

| Field | Description |
| --- | --- |
| `logID` | Unique ID for this log event |
| `transactionID` | Transaction associated with the event |
| `customerID` | Customer involved in the transaction (the *subject* of the transaction) |
| `actorID` | Person/system that performed the action (the *actor*, e.g. the pharmacist) |
| `actorRole` | Role of the actor |
| `action` | What happened |
| `dateTime` | When it happened |
| `details` | Optional readable explanation |

**Enum `action`:** `COMPLETE`, `CANCELLED`

- **`COMPLETE`** — transaction successfully processed
- **`CANCELLED`** — transaction was started but cancelled before completion (e.g. customer changed their mind, insufficient balance). If "Void" cancels a pharmacist's in-progress cart, the app translates that into a `CANCELLED` log entry once a transaction record/ID exists

**Examples:**

```
LOG-001,TXN-001,CUS-001,PHA-001,PHARMACIST,COMPLETE,2026-09-11 14:30,Transaction completed successfully
LOG-002,TXN-002,CUS-002,PHA-001,PHARMACIST,CANCELLED,2026-09-11 15:10,Customer changed their mind before payment
LOG-003,TXN-003,CUS-003,PHA-002,PHARMACIST,CANCELLED,2026-09-11 16:20,Insufficient balance
```

> **Note on the schema evolution:** this was originally proposed with a dedicated `pharmacistID` column instead of `actorID`/`actorRole`. That was changed to keep the pattern consistent with every other log table (`actorID` + `actorRole`), so it isn't unnecessarily tied to the current role structure. The purchased items themselves live in `transactionItemsDB.csv`; this log just records the *event*.
> 

---

## 13. balanceLogs.csv

**Schema:** `logID,customerID,actorID,actorRole,action,amount,balanceBefore,balanceAfter,dateTime,details`

| Field | Description |
| --- | --- |
| `logID` | Unique ID for the balance event |
| `customerID` | Customer whose balance changed |
| `actorID` | Person/system responsible for the change |
| `actorRole` | Role of the actor |
| `action` | Type of balance change |
| `amount` | Amount added or deducted (always positive — `action` determines direction) |
| `balanceBefore` | Balance immediately before the change |
| `balanceAfter` | Balance immediately after the change |
| `dateTime` | When the change happened |
| `details` | Optional explanation |

**Enum `action`:** `TOP_UP`, `PURCHASE`, `ADJUSTMENT`

**Examples:**

```
LOG-001,CUS-001,CUS-001,CUSTOMER,TOP_UP,500.00,100.00,600.00,2026-09-11 10:00,Customer topped up account
LOG-002,CUS-001,PHA-001,PHARMACIST,PURCHASE,250.00,600.00,350.00,2026-09-11 14:30,Deducted for TXN-001
LOG-003,CUS-001,ADM-001,ADMIN,ADJUSTMENT,50.00,350.00,400.00,2026-09-11 15:00,Corrected balance due to account error
```

> **Why `balanceBefore`/`balanceAfter`?** They're historical snapshots, not redundant with `customerDB.balance` (which is the *current* state). Seeing `₱600 → ₱350` is far more useful for an audit trail than just `PURCHASE -₱250`, since it lets you verify the account's exact state at any point without reconstructing it from every prior event.
> 

---

## Logging design principles

A few patterns apply across all five log tables:

- **Consistent actor pattern:** every log uses `actorID` + `actorRole` (not table-specific columns like `pharmacistID`) so the logging pattern stays uniform across the system.
- **Intentional redundancy is OK:** e.g. `inventoryLogs` and `batchLogs` both carry `itemID` even though it's derivable via `batchID → batchDB → itemID`. This lets you query "all movement for ITM-001" directly without joining through `batchDB`, and the log stays accurate even if the batch record later changes. The goal isn't zero duplication — it's avoiding **two tables trying to represent the same current state**.
- **Logs are independently readable:** each log should be understandable without joining against the "live" table it relates to (e.g. `transactionLogs` carries `customerID` even though `transactionsDB` also has it).

| Log | What it records |
| --- | --- |
| `prescLogs.csv` | Prescription lifecycle events |
| `inventoryLogs.csv` | Quantity/stock movement |
| `batchLogs.csv` | Batch lifecycle changes |
| `transactionLogs.csv` | Completed/cancelled transaction events |
| `balanceLogs.csv` | Customer balance changes |

---

*Last updated from chat history review — Sept 2026. Update this page directly as schemas evolve; no need to re-derive from chat history once a schema changes going forward.*
