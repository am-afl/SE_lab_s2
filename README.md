# مرحله اول - بررسی کد `PaymentProcessor`

## معرفی کلی

کد شامل یک کلاس به نام `PaymentProcessor` است که وظیفه پردازش پرداخت‌ها را بر عهده دارد. این کلاس از سه روش پرداخت مختلف پشتیبانی می‌کند:

- پرداخت با کارت اعتباری (`credit_card`)
- پرداخت با کیف پول دیجیتال (`digital_wallet`)
- پرداخت با انتقال بانکی (`bank_transfer`)

در ادامه، بخش‌های مختلف کلاس به تفکیک بررسی می‌شوند:

---

## ساختار کلاس

### 🔹 Constructor

سازنده کلاس یک فیلد `config` دریافت می‌کند که حاوی تنظیمات مربوط به API‌های پرداخت است و در هنگام ساخت نمونه به کلاس تزریق می‌شود.

---

### 🔹 تابع `processPayment`

تابع اصلی پردازش پرداخت است که مراحل زیر را انجام می‌دهد:

1. اعتبارسنجی اطلاعات پرداخت با استفاده از `validatePayment`
2. انتخاب متد مناسب براساس نوع پرداخت:
   - `processCreditCard`
   - `processDigitalWallet`
   - `processBankTransfer`
3. ثبت لاگ تراکنش با استفاده از `logTransaction`
4. بازگرداندن نتیجه پرداخت

---

### 🔹 تابع `validatePayment`

وظیفه این تابع بررسی درستی اطلاعات تراکنش است. مراحل بررسی:

- مبلغ باید بیشتر از صفر باشد
- ارز باید یکی از موارد `USD`، `EUR` یا `GBP` باشد
- اطلاعات مشتری باید شامل ایمیل باشد

سپس بر اساس نوع پرداخت، فیلدهای خاص نیز بررسی می‌شوند (مثلاً شماره کارت یا شماره حساب).

---

### 🔹 توابع `processCreditCard`، `processDigitalWallet`، `processBankTransfer`

این توابع به API مربوطه متصل می‌شوند، یک `transactionID` ایجاد می‌کنند و نتیجه موفقیت‌آمیز را بازمی‌گردانند.

- تفاوت اصلی آن‌ها در **آدرس API** و **پیشوند شناسه تراکنش** است (مثل `CC`، `DW`، `BT`).

---

### 🔹 تابع `logTransaction`

این تابع اطلاعات تراکنش را به صورت یک رشته فرمت‌شده در خروجی چاپ می‌کند.

---

### 🔹 تابع `main`

در این بخش نمونه‌ای از `PaymentProcessor` ساخته شده و یک تراکنش تستی با کارت اعتباری انجام می‌شود.

نمونه‌ای از خروجی:


Connecting to Credit Card API at https://api.creditcard.com/process Processing credit card payment for John Doe LOG: Sat Apr 05 00:19:37 IRST 2025 - credit_card payment of 100.00 USD for John Doe: {status=success, transaction_id=CC1743799777958} Final Result: {status=success, transaction_id=CC1743799777958}


---

## مشکلات طراحی (Code Smell و SOLID)

### ⚠️ متد `processPayment` طولانی است

این متد چندین وظیفه دارد:
- اعتبارسنجی
- تصمیم‌گیری در مورد روش پرداخت
- ثبت لاگ
- بازگرداندن نتیجه

این نقض اصل **SRP (Single Responsibility Principle)** است. هر متد باید فقط یک مسئولیت داشته باشد.

---

### ⚠️ تکرار کد در متدهای `processCreditCard`, `processDigitalWallet`, `processBankTransfer`

در تمام این متدها ساختار مشابهی دیده می‌شود. تنها تفاوت در آدرس API و پیشوند شناسه تراکنش است. این تکرار را می‌توان با استفاده از یک الگو یا متد مشترک حذف کرد.

---

### ⚠️ ترکیب وظایف و مسئولیت نامشخص در `processPayment`

در پروژه‌های بزرگ، چنین توابعی باید به متدها و کلاس‌های جداگانه تقسیم شوند تا:
- خوانایی افزایش یابد
- قابلیت تست بهتر شود
- نگهداری و توسعه ساده‌تر گردد

---

# مرحله اول - مستندسازی

## code smell های شناسایی شده
| شماره | نوع Code Smell         | توضیح |
|-------|-------------------------|--------|
| 1     | Long Method             | متد `processPayment` وظایف زیادی را انجام می‌دهد (اعتبارسنجی، تصمیم‌گیری، پردازش، لاگ‌گیری). |
| 2     | God Class               | کلاس `PaymentProcessor` هم مسئول پردازش است، هم اعتبارسنجی، هم لاگ‌گیری – یعنی چند مسئولیت در یک کلاس. |
| 3     | Duplicate Code          | متدهای `processCreditCard`, `processDigitalWallet`, `processBankTransfer` ساختار بسیار مشابهی دارند. |
| 4     | Primitive Obsession     | استفاده از `Map<String, String>` به‌جای کلاس‌های مشخص برای مشتری و اطلاعات پرداخت، خوانایی و اطمینان کد را کاهش می‌دهد. |
| 5     | Switch Statements       | استفاده از `switch(paymentType)` نشانه‌ای از عدم استفاده از اصول شی‌ءگرایی و وجود شرط‌های پیچیده است. |

## اصول SOLID نقض‌شده
| اصل  | نام کامل                        | توضیح نقض |
|------|----------------------------------|------------|
| S    | Single Responsibility Principle | کلاس `PaymentProcessor` بیش از یک وظیفه دارد (هم پردازش، هم اعتبارسنجی، هم لاگ‌گیری). |
| O    | Open/Closed Principle           | اضافه‌کردن نوع جدید پرداخت (مثل `crypto`) نیازمند ویرایش `processPayment` و `validatePayment` است؛ کد برای توسعه بسته نیست. |
| L    | Liskov Substitution Principle   | این اصل مستقیماً نقض نشده، ولی به دلیل نبود abstraction برای handlerها، قابلیت جایگزینی وجود ندارد. |
| I    | Interface Segregation Principle | پیاده‌سازی نشده، ولی با اضافه‌کردن interfaceهای تخصصی می‌توان بهبودش داد. |
| D    | Dependency Inversion Principle  | کلاس `PaymentProcessor` مستقیماً وابسته به جزئیات (`System.out.println`, `Map<String, String>`) است؛ وابستگی به abstraction وجود ندارد. |

