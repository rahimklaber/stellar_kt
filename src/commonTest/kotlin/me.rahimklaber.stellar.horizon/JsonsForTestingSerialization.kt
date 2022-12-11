package me.rahimklaber.stellar.horizon

val accountResponses1 = """
    {
      "_links": {
        "self": {
          "href": "https://horizon.stellar.org/accounts/?cursor=&limit=10&order=asc&signer=GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6"
        },
        "next": {
          "href": "https://horizon.stellar.org/accounts/?cursor=GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6&limit=10&order=asc&signer=GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6"
        },
        "prev": {
          "href": "https://horizon.stellar.org/accounts/?cursor=GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6&limit=10&order=desc&signer=GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6"
        }
      },
      "_embedded": {
        "records": [
          {
            "_links": {
              "self": {
                "href": "https://horizon.stellar.org/accounts/GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6"
              },
              "transactions": {
                "href": "https://horizon.stellar.org/accounts/GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6/transactions{?cursor,limit,order}",
                "templated": true
              },
              "operations": {
                "href": "https://horizon.stellar.org/accounts/GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6/operations{?cursor,limit,order}",
                "templated": true
              },
              "payments": {
                "href": "https://horizon.stellar.org/accounts/GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6/payments{?cursor,limit,order}",
                "templated": true
              },
              "effects": {
                "href": "https://horizon.stellar.org/accounts/GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6/effects{?cursor,limit,order}",
                "templated": true
              },
              "offers": {
                "href": "https://horizon.stellar.org/accounts/GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6/offers{?cursor,limit,order}",
                "templated": true
              },
              "trades": {
                "href": "https://horizon.stellar.org/accounts/GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6/trades{?cursor,limit,order}",
                "templated": true
              },
              "data": {
                "href": "https://horizon.stellar.org/accounts/GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6/data/{key}",
                "templated": true
              }
            },
            "id": "GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6",
            "account_id": "GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6",
            "sequence": "159005229805814667",
            "subentry_count": 0,
            "last_modified_ledger": 37650452,
            "last_modified_time": "2021-10-04T13:42:54Z",
            "thresholds": {
              "low_threshold": 0,
              "med_threshold": 0,
              "high_threshold": 0
            },
            "flags": {
              "auth_required": false,
              "auth_revocable": false,
              "auth_immutable": false,
              "auth_clawback_enabled": false
            },
            "balances": [
              {
                "balance": "232.1959972",
                "buying_liabilities": "0.0000000",
                "selling_liabilities": "0.0000000",
                "asset_type": "native"
              }
            ],
            "signers": [
              {
                "weight": 1,
                "key": "GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6",
                "type": "ed25519_public_key"
              }
            ],
            "data": {},
            "num_sponsoring": 0,
            "num_sponsored": 0,
            "paging_token": "GDU5VNXTKRHXKM4EHWUX3MEDHRCZ65FKSMQZAJHQYKQN7A6ZT4NQZWJ6"
          }
        ]
      }
    }
""".trimIndent()