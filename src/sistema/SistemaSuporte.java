package sistema;

import java.util.Scanner;
import java.util.InputMismatchException;
import estruturas.*;
import model.*;

public class SistemaSuporte {
    private ArvoreUsuarios usuarios = new ArvoreUsuarios();
    private ListaChamados listaChamados = new ListaChamados();
    private FilaHistorico historico = new FilaHistorico();
    private ArvoreChamados arvore = new ArvoreChamados();
    private PilhaDesfazer pilhaDesfazer = new PilhaDesfazer();
    private Scanner scanner = new Scanner(System.in);

    public void executar() {
        int opcao;

        do {
            limparTela();
            System.out.println();
            System.out.println(" .d8888b.  888     888 8888888b.   .d88888b.  8888888b.  88888888888 8888888888");
            System.out.println("d88P  Y88b 888     888 888   Y88b d88P\" \"Y88b 888   Y88b     888     888       ");
            System.out.println("Y88b.      888     888 888    888 888     888 888    888     888     888       ");
            System.out.println(" \"Y888b.   888     888 888   d88P 888     888 888   d88P     888     8888888   ");
            System.out.println("    \"Y88b. 888     888 8888888P\"  888     888 8888888P\"      888     888       ");
            System.out.println("      \"888 888     888 888        888     888 888 T88b       888     888       ");
            System.out.println("Y88b  d88P Y88b. .d88P 888        Y88b. .d88P 888  T88b      888     888       ");
            System.out.println(" \"Y8888P\"   \"Y88888P\"  888         \"Y88888P\"  888   T88b     888     8888888888");
            System.out.println();
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Listar usuários");
            System.out.println("3. Abrir chamado");
            System.out.println("4. Atender chamado");
            System.out.println("5. Listar chamados pendentes");
            System.out.println("6. Histórico de atendimentos");
            System.out.println("7. Buscar chamado por código");
            System.out.println("8. Desfazer última ação");
            System.out.println("0. Sair");
            System.out.println();
            System.out.print("Escolha: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    aguardarEnter();
                    break;
                case 2:
                    usuarios.listar();
                    aguardarEnter();
                    break;
                case 3:
                    abrirChamado();
                    aguardarEnter();
                    break;
                case 4:
                    atenderChamado();
                    aguardarEnter();
                    break;
                case 5:
                    listaChamados.listar();
                    aguardarEnter();
                    break;
                case 6:
                    historico.listar();
                    aguardarEnter();
                    break;
                case 7:
                    buscarChamado();
                    aguardarEnter();
                    break;
                case 8:
                    desfazerAcao();
                    aguardarEnter();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    aguardarEnter();
                    break;
            }
        } while (opcao != 0);
    }

    private void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void aguardarEnter() {
        System.out.print("\nPressione Enter para voltar ao menu...");
        scanner.nextLine();
    }

    private int lerInteiro() {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Entrada inválida. Digite um número: ");
            }
        }
    }

    private int lerInteiroPositivo(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            int valor = lerInteiro();
            if (valor > 0) return valor;
            System.out.println("Valor deve ser maior que zero.");
        }
    }

    private String lerTextoNaoVazio(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) return texto;
            System.out.println("Campo obrigatório, não pode ser vazio.");
        }
    }

    private String lerTextoApenasLetras(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty() && texto.matches("[a-zA-ZÀ-ÿ ]+")) return texto;
            System.out.println("Campo inválido. Use apenas letras.");
        }
    }

    private void cadastrarUsuario() {
        int id;
        while (true) {
            id = lerInteiroPositivo("ID: ");
            if (usuarios.buscarPorId(id) == null) break;
            System.out.println("Já existe um usuário com esse ID.");
        }

        String nome = lerTextoApenasLetras("Nome: ");
        String setor = lerTextoNaoVazio("Setor: ");

        Usuario usuario = new Usuario(id, nome, setor);
        usuarios.inserir(usuario);
        pilhaDesfazer.registrar("USUARIO", usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void abrirChamado() {
        int codigo;
        while (true) {
            codigo = lerInteiroPositivo("Código do chamado: ");
            if (arvore.buscar(codigo) == null) break;
            System.out.println("Já existe um chamado com esse código.");
        }

        int idUsuario = lerInteiroPositivo("ID do usuário: ");
        Usuario usuario = usuarios.buscarPorId(idUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        String descricao = lerTextoNaoVazio("Descrição do problema: ");

        String prioridade;
        while (true) {
            System.out.print("Prioridade (Baixa/Media/Alta): ");
            prioridade = scanner.nextLine().trim();
            if (prioridade.equalsIgnoreCase("Baixa") ||
                prioridade.equalsIgnoreCase("Media") ||
                prioridade.equalsIgnoreCase("Alta")) break;
            System.out.println("Prioridade inválida. Digite Baixa, Media ou Alta.");
        }

        Chamado chamado = new Chamado(codigo, descricao, usuario, prioridade);
        listaChamados.adicionar(chamado);
        arvore.inserir(chamado);
        pilhaDesfazer.registrar("CHAMADO", chamado);
        System.out.println("Chamado registrado com sucesso!");
    }

    private void atenderChamado() {
        if (listaChamados.estaVazia()) {
            System.out.println("Nenhum chamado pendente.");
            return;
        }

        Chamado atendido = listaChamados.atender();
        historico.registrar(atendido);
        System.out.println("Chamado atendido:");
        System.out.println(atendido);
    }

    private void buscarChamado() {
        System.out.print("Digite o código: ");
        int codigo = lerInteiro();

        Chamado chamado = arvore.buscar(codigo);
        if (chamado != null) {
            System.out.println(chamado);
        } else {
            System.out.println("Chamado não encontrado.");
        }
    }

    private void desfazerAcao() {
        if (pilhaDesfazer.estaVazia()) {
            System.out.println("Nenhuma ação para desfazer.");
            return;
        }

        String tipo   = pilhaDesfazer.getTipoTopo();
        Object objeto = pilhaDesfazer.getObjetoTopo();
        pilhaDesfazer.removerTopo();

        if (tipo.equals("USUARIO")) {
            Usuario u = (Usuario) objeto;
            usuarios.remover(u.getId());
            System.out.println("Desfeito: cadastro do usuário " + u.getNome() + " removido.");
        } else if (tipo.equals("CHAMADO")) {
            Chamado c = (Chamado) objeto;
            listaChamados.removerPorCodigo(c.getCodigo());
            arvore.remover(c.getCodigo());
            System.out.println("Desfeito: chamado #" + c.getCodigo() + " removido.");
        }
    }
}