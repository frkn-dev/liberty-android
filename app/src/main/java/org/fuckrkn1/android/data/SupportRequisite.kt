package org.fuckrkn1.android.data

data class SupportRequisite(
    val name: String,
    val account: String,
) {
    companion object {
        val ALL = listOf(
            SupportRequisite(
                name = "USDT(Tether)",
                account = "0xE92d1695483bd9E82A1AeEEa02E60797B055c53C",
            ),
            SupportRequisite(
                name = "BTC",
                account = "bc1qe76609xu8qay4wt7tnvrxhx4spnjrywwh2jwf3",
            ),
            SupportRequisite(
                name = "Ethereum",
                account = "0xE92d1695483bd9E82A1AeEEa02E60797B055c53C",
            ),
            SupportRequisite(
                name = "NEAR",
                account = "2e1a065b84f27e3e8b7c9cd64200b7cf00ccd7b624da5996aba1185408ba5b5b",
            ),
            SupportRequisite(
                name = "Credo (Georgian Card)",
                account = "GE21CD0360000028302649"
            )
        )
    }
}