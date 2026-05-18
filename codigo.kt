data class Pedido(
    val id: Int,
    val status: StatusPedido
)

enum class StatusPedido {
    NOVO,
    PROCESSADO,
    DESCONHECIDO
}

interface ProcessadorStatus {
    fun processar(pedido: Pedido)
}

class NovoPedidoProcessor : ProcessadorStatus {
    override fun processar(pedido: Pedido) {
        println("Pedido em processamento: ${pedido.id}")
        simularProcessamento()
    }

    private fun simularProcessamento() {
        Thread.sleep(2000)
    }
}

class PedidoProcessadoProcessor : ProcessadorStatus {
    override fun processar(pedido: Pedido) {
        println("Pedido já processado: ${pedido.id}")
    }
}

class PedidoDesconhecidoProcessor : ProcessadorStatus {
    override fun processar(pedido: Pedido) {
        println("Status desconhecido do pedido: ${pedido.id}")
    }
}

class PedidoProcessor {

    private val processadores = mapOf(
        StatusPedido.NOVO to NovoPedidoProcessor(),
        StatusPedido.PROCESSADO to PedidoProcessadoProcessor(),
        StatusPedido.DESCONHECIDO to PedidoDesconhecidoProcessor()
    )

    fun processarPedido(pedido: Pedido) {
        val processador =
            processadores[pedido.status] ?: PedidoDesconhecidoProcessor()

        processador.processar(pedido)
    }
}

1. Quais problemas você identificou no código original?

O código possuía lógica centralizada, baixa organização, uso de strings para status e dificuldade para adicionar novas funcionalidades, tornando a manutenção mais complicada.

2. Como a refatoração melhorou a organização e legibilidade do código?

A refatoração separou as responsabilidades em classes específicas, deixando o código mais limpo, organizado e fácil de entender.

3. Quais padrões de design foram aplicados e por quê?

Foi aplicado o padrão Strategy para separar os diferentes tipos de processamento de pedidos, além do princípio SRP para manter cada classe com apenas uma responsabilidade.

4. Como essas mudanças impactam a manutenção e escalabilidade do código no longo prazo?

As mudanças facilitam futuras alterações e expansões no sistema sem afetar o restante do código, melhorando a manutenção e escalabilidade.

5. Quais boas práticas foram aplicadas durante a refatoração?

Foram aplicadas separação de responsabilidades, organização modular, uso de enum, melhoria da legibilidade e padrões de design para tornar o código mais seguro e reutilizável.
