package me.rahimklaber.stellar.horizon.operations.testresponses

val accountMergeResponseJson1 = """
    {
      "_links": {
        "self": {
          "href": "https://horizon.stellar.org/operations/121887714411839489"
        },
        "transaction": {
          "href": "https://horizon.stellar.org/transactions/02077009a551ec94c776f83529293dcfc1c2cd5b38af043ef7f3699bf5a71f0a"
        },
        "effects": {
          "href": "https://horizon.stellar.org/operations/121887714411839489/effects"
        },
        "succeeds": {
          "href": "https://horizon.stellar.org/effects?order=desc\u0026cursor=121887714411839489"
        },
        "precedes": {
          "href": "https://horizon.stellar.org/effects?order=asc\u0026cursor=121887714411839489"
        }
      },
      "id": "121887714411839489",
      "paging_token": "121887714411839489",
      "transaction_successful": true,
      "source_account": "GCVLWV5B3L3YE6DSCCMHLCK7QIB365NYOLQLW3ZKHI5XINNMRLJ6YHVX",
      "type": "account_merge",
      "type_i": 8,
      "created_at": "2020-02-24T17:03:00Z",
      "transaction_hash": "02077009a551ec94c776f83529293dcfc1c2cd5b38af043ef7f3699bf5a71f0a",
      "account": "GCVLWV5B3L3YE6DSCCMHLCK7QIB365NYOLQLW3ZKHI5XINNMRLJ6YHVX",
      "into": "GATL3ETTZ3XDGFXX2ELPIKCZL7S5D2HY3VK4T7LRPD6DW5JOLAEZSZBA"
    }
""".trimIndent()