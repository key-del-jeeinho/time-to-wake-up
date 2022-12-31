## Domains
- Account(idx, name, id, encoded_password)
- CreateAccount(name, id, password) -> Account

## API endpoints
- **create account**
  `HTTP#POST` /api/v1/accounts


- **get all account data**
  `HTTP#GET` /api/v1/accounts


- **get account data by id**
  `HTTP#GET` /api/v1/accounts/:idx


- **change account data need to be authenticated**
  `HTTP#PATCH` /api/v1/accounts/:idx


- **delete account by id need to be authenticated**
  `HTTP#DELETE` /api/v1/accounts/:idx