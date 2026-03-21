import java.nio.charset.StandardCharsets;
import java.util.Scanner;
 
public class HotelTransilvania{

    // ===== VARIÁVEIS GLOBAIS =====
    static int tamanho = 0;          // Controla quantos espaços do vetor estão ocupados
    static int numeroReserva = 1;    // Gera números sequenciais para cada nova reserva
    
    /**
     * MÉTODO: CadastrarHospede
     * FUNÇÃO: Adiciona ou remove um hóspede do vetor
     * 
     * Funciona de duas formas:
     * - Se nome != null: CADASTRA novo hóspede (preenche 3 posições: reserva, quarto, nome)
     * - Se nome == null: REMOVE hóspede (limpa as 3 posições)
     */
    public static void CadastrarHospede(String[] vetor, String nome, String numeroQuarto, int posicao){
        
        String numeroReservaString = "";

        if(nome != null){
            // CADASTRO: Gera número de reserva automático
            numeroReservaString = String.valueOf(numeroReserva);
        } else{
            // REMOÇÃO: Limpa os dados e ajusta contadores
            numeroReservaString = null;
            numeroReserva--;
            tamanho -= 3;
        }
        // Cada hóspede ocupa 3 posições consecutivas no vetor
        vetor[posicao] = numeroReservaString;      // Posição 0: Número da Reserva
        vetor[posicao + 1] = numeroQuarto;         // Posição 1: Número do Quarto
        vetor[posicao + 2] = nome;                 // Posição 2: Nome do Hóspede
        
        numeroReserva++;
        tamanho += 3;
    }

    /**
     * MÉTODO: TemHospedes
     * FUNÇÃO: Verifica se existe pelo menos um hóspede cadastrado no hotel
     * Percorre o vetor de 3 em 3 posições e verifica se o campo "quarto" não é nulo
     */
    public static boolean TemHospedes(String[] hospedes){
        for (int x = 0; x < hospedes.length; x += 3) {
            if (hospedes[x + 1] != null) {  // Verifica se tem número de quarto cadastrado
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in, StandardCharsets.UTF_8);

        // ===== VETOR DE STATUS DOS QUARTOS =====
        // Cada posição representa um quarto (índice 0 = quarto 1, índice 99 = quarto 100)
        // true = ocupado, false = disponível
        boolean[] statusQuarto = new boolean[100];
        for(int i = 0; i < statusQuarto.length; i++){
            statusQuarto[i] = false; // Inicialmente, todos os quartos estão disponíveis
        }

        // ===== VETOR DE HÓSPEDES =====
        // Estrutura: cada hóspede ocupa 3 posições consecutivas
        // [reserva, quarto, nome] para cada quarto
        // Posição do quarto 1: índices 0,1,2 | Quarto 2: índices 3,4,5 | etc.
        String[] hospedes = new String[300]; // 100 quartos x 3 informações = 300 posições

        // ===== MATRIZ DE PRODUTOS =====
        // Define os produtos disponíveis no frigobar com seus respectivos preços
        String[][] produtos = {
            {"Agua", "5,00"},
            {"Suco", "8,00"},
            {"Refrigerante 300ml", "7,00"},
            {"Energético", "9,00"},
            {"Barra de Chocolate", "16,00"},
            {"Salgadinho", "10,00"}
        };

        // ===== MATRIZ DE CONSUMO DO FRIGOBAR =====
        // LINHAS: representam os quartos (0 a 99)
        // COLUNAS: representam os produtos (0 a 5)
        // Cada posição armazena a QUANTIDADE consumida daquele produto no quarto
        float[][] frigobar = new float[100][produtos.length];

        int valorDiaria = 800; // Valor fixo da diária

        System.out.println("Bem-vindo ao Sistema do Hotel Transilvânia!");

        // ===== MENU PRINCIPAL =====
        while(true)
        {
            System.out.print("\nPor favor, o que deseja?\n" + 
            "[1] - Cadastrar\n" +
            "[2] - Cancelar Reserva\n" +
            "[3] - Listar Reservas\n" +
            "[4] - Consultar Hóspede\n" +
            "[5] - Editar Hóspede\n" +
            "[6] - Registar Consumo do Frigobar\n" +
            "[7] - Check-out\n" +
            "[8] - Sair :(\n" +
            "R: ");

            int opcao = scan.nextInt();
            scan.nextLine(); 

            switch (opcao) {
                // ===== CASO 1: CADASTRAR HÓSPEDE =====
                case 1:
                    if(tamanho < 300){  // Verifica se ainda há espaço no hotel
                        System.out.print("Digite o número do quarto para cadastrar: ");
                        String numeroQuartoString = scan.nextLine();
                        int numeroQuarto = Integer.parseInt(numeroQuartoString);

                        if(!statusQuarto[numeroQuarto - 1]){  // Verifica se quarto está disponível
                            System.out.print("Digite o nome do hóspede: ");
                            String nome = scan.nextLine();

                            // Cadastra no vetor de hóspedes (posição = (quarto-1) * 3)
                            CadastrarHospede(hospedes, nome, numeroQuartoString, ( numeroQuarto - 1 ) * 3);

                            statusQuarto[numeroQuarto - 1] = true;  // Marca quarto como ocupado
                            System.out.println("Hóspede Cadastrado com Sucesso!");
                        } else {
                            System.out.println("Quarto n°" + numeroQuarto + " está ocupado!!!");
                        }
                    } else{
                        System.out.println("TODOS OS QUARTOS ESTAO CHEIOS!!!");
                    }
                    break;
                    
                // ===== CASO 2: CANCELAR RESERVA =====
                case 2:
                    System.out.print("\nDigite o número do quarto para cancelar: ");
                    int numeroQuartoCancelar = scan.nextInt();
                    
                    if(statusQuarto[numeroQuartoCancelar - 1])  // Verifica se quarto está ocupado
                    {
                        System.out.print("Deseja mesmo cancelar esse hóspede?\n" +
                        "[S] - Sim\n" +
                        "[N] - Nao\n" +
                        "R: ");

                        scan.nextLine();
                        String confirmacao = scan.nextLine().toLowerCase();

                        if(confirmacao.equals("s") || confirmacao.equals("sim")){
                            // Remove o hóspede (passando null no nome)
                            CadastrarHospede(hospedes, null, null, (numeroQuartoCancelar - 1) * 3);
                            statusQuarto[numeroQuartoCancelar - 1] = false;  // Libera o quarto
                            System.out.println("Reserva do Quarto n°" + numeroQuartoCancelar + " cancelada com Sucesso!");
                        }
                        else{
                            System.out.println("OPERACAO CANCELADA!!!");
                        }
                    }
                    else
                    {
                        System.out.println("QUARTO SEM HOSPEDE PARA CANCELAMENTO!!!");
                    }
                    break;

                // ===== CASO 3: LISTAR RESERVAS =====
                case 3:
                    if(TemHospedes(hospedes))  // Verifica se há hóspedes cadastrados
                    {
                        System.out.println("\nLista de Reservas:\n");
                        // Percorre vetor de 3 em 3 posições
                        for (int x = 0; x < hospedes.length; x += 3) {
                            if (hospedes[x] != null) {  // Verifica se tem reserva
                                System.out.println(
                                    "=========================================================================\n" +
                                    "Reserva: " + hospedes[x] + 
                                    "\nQuarto: " + hospedes[x + 1] + 
                                    "\nNome: " + hospedes[x + 2] +
                                    "\n=========================================================================");
                            }
                        }
                    } else{
                        System.out.println("\nNENHUM HOSPEDE ESTA CADASTRADO ATUALMENTE!!!");
                    }
                    break;

                // ===== CASO 4: CONSULTAR HÓSPEDE =====
                case 4:
                    System.out.print("\nDigite o número do quarto para consultar: ");
                    int numeroQuartoConsultar = scan.nextInt() - 1;

                    // Calcula posição correta no vetor: quarto * 3 + 1 (campo número do quarto)
                    if (hospedes[numeroQuartoConsultar * 3 + 1] != null) {
                        System.out.println(
                            "\n                          Cadastro do Hóspede" +
                            "\n=========================================================================\n" +
                            "Reserva: " + hospedes[numeroQuartoConsultar * 3] + 
                            "\nQuarto: " + hospedes[numeroQuartoConsultar * 3 + 1] + 
                            "\nNome: " + hospedes[numeroQuartoConsultar * 3 + 2] + 
                            "\n=========================================================================");
                    } else {
                        System.out.println("RESERVA NAO ENCONTRADA!!!");
                    }
                    break;

                // ===== CASO 5: EDITAR HÓSPEDE =====
                case 5:
                    if( tamanho < 300 )
                    {
                        System.out.print(
                            "                           Editar Hóspede" +
                            "\n=========================================================================\n" +
                            "Digite o número do quarto para editar: "
                        );
                        int numeroQuartoEditar = scan.nextInt() - 1;
                        scan.nextLine();

                        String numeroQuartoEditarString = String.valueOf(numeroQuartoEditar + 1);
                        if (statusQuarto[numeroQuartoEditar])  // Verifica se quarto está ocupado
                        {
                            // Exibe dados atuais do hóspede
                            System.out.println(
                                "\n                            Cadastro do Hóspede" +
                                "\n=========================================================================\n" +
                                "Reserva: " + hospedes[numeroQuartoEditar * 3] + 
                                "\nQuarto: " + numeroQuartoEditarString + 
                                "\nNome: " + hospedes[numeroQuartoEditar * 3 + 2] + 
                                "\n=========================================================================\n"
                            );

                            System.out.print("Digite o novo número da Reserva: ");
                            String novoNumeroReserva = scan.nextLine();
                            
                            System.out.print("Digite o novo nome do hóspede: ");
                            String novoNome = scan.nextLine();
                            
                            // Atualiza os dados (remove antigo e cadastra novo)
                            CadastrarHospede(hospedes, novoNome, numeroQuartoEditarString, numeroQuartoEditar * 3);
                            hospedes[numeroQuartoEditar * 3] = novoNumeroReserva;  // Atualiza número da reserva

                            System.out.println("Hóspede Editado com Sucesso!");
                        } 
                        else 
                        {
                            System.out.println("Quarto n°" + (numeroQuartoEditarString + 1) + " desocupado!!!");
                        }
                    }
                    else
                    {
                        System.out.println("TODOS OS QUARTOS ESTAO CHEIOS!!!");
                    }
                    break;

                // ===== CASO 6: REGISTRAR CONSUMO DO FRIGOBAR =====
                case 6:
                    System.out.println(
                        "                       Registar Consumo do Frigobar" +
                        "\n=========================================================================\n"
                    );
                    
                    System.out.print("Digite o Número do Quarto do Hóspede: ");
                    int numeroQuarto = scan.nextInt();
                    scan.nextLine();
                    System.out.println("");

                    if(statusQuarto[numeroQuarto - 1]){  // Verifica se quarto está ocupado
                        // Exibe lista de produtos disponíveis
                        int x = 0;
                        for(String[] produto : produtos){
                            System.out.println("[" + (x + 1) +"] - " + produto[0] + " -> R$" + produto[1]);
                            x++;
                        }

                        // Formato: produto,quantidade,produto,quantidade
                        System.out.print(
                            "\nEx: 1(Produto), 10(Quantidade), 4(Produto), 5(Quantidade)" +
                            "\nR: "
                        );

                        String leitura = scan.nextLine().strip();
                        String[] numeros = leitura.split(",");
                        
                        if(numeros.length % 2 != 0){
                            System.out.println("Entrada inválida! Certifique-se de seguir o formato correto.");
                            break;
                        }

                        // Adiciona consumo na matriz frigobar
                        for(x = 0; x < numeros.length; x += 2){
                            // Linha: quarto, Coluna: produto, Valor: quantidade
                            frigobar[numeroQuarto - 1][Integer.parseInt(numeros[x].strip()) - 1] += Integer.parseInt(numeros[x + 1].strip());
                        }

                        System.out.println("Consumo registrado com sucesso!");
                    }
                    else
                    {
                        System.out.println("QUARTO NAO CADASTRADO!!!");
                    }
                    break;

                // ===== CASO 7: CHECK-OUT =====
                case 7:
                    System.out.print(
                        "\n                             Check-out" +
                        "\n=========================================================================" +
                        "\nDigite o Número do Quarto do Hóspede: "
                    );
                    numeroQuarto = scan.nextInt();
                    
                    if(!statusQuarto[numeroQuarto - 1])
                    {
                        System.out.println("QUARTO NAO ESTA OCUPADO!!!");
                        break;
                    }

                    System.out.print("Digite a quantidade de Diárias á ser paga: ");
                    int qntDias = scan.nextInt();

                    // CÁLCULO DA CONTA
                    int valorDiarias = qntDias * valorDiaria;
                    float valorProdutos = 0;

                    // Calcula total consumido no frigobar
                    for(int x = 0; x < produtos.length; x++){
                        valorProdutos += frigobar[numeroQuarto - 1][x] * Float.parseFloat(produtos[x][1].replace(",", "."));
                    }

                    float valorTotal = valorDiarias + valorProdutos;
                    
                    // EXIBE RESUMO DA CONTA
                    System.out.println(
                        "\n                             Resumo da Conta" +
                        "\n=========================================================================\n" +
                        "Nome: " + hospedes[(numeroQuarto - 1) * 3 + 2] +
                        "\nQuarto: " + hospedes[(numeroQuarto - 1) * 3 + 1] +
                        "\nTotal de Diárias: " + qntDias +
                        "\nValor por Diária: R$" + valorDiaria +
                        "\nValor Total das Diárias: R$" + valorDiarias + 
                        "\nValor Total dos Produtos do Frigobar: R$" + valorProdutos + 
                        "\nValor Total a ser Pago: R$" + valorTotal + 
                        "\n========================================================================="
                    );

                    // LIBERA O QUARTO PARA PRÓXIMOS HÓSPEDES
                    statusQuarto[numeroQuarto - 1] = false;  // Marca quarto como disponível
                    CadastrarHospede(hospedes, null, null, (numeroQuarto - 1) * 3);  // Remove hóspede
                    frigobar[numeroQuarto - 1] = new float[produtos.length];  // Zera consumo do frigobar

                    System.out.println("Check-out realizado com sucesso!");
                    break;

                // ===== CASO 8: SAIR =====
                case 8:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                    
                default:
                    System.out.println("Opçao inválida. Por favor, tente novamente.");
                    break;
            }

            if(opcao == 8){
                break;  // Sai do loop principal
            }
        }
        scan.close();
    }
}