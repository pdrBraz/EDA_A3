package sistema;

import java.util.Scanner;
import estruturas.*;
import model.*;

public class SistemaSuporte {
    private ListaUsuarios usuarios = new ListaUsuarios();
    private FilaChamados fila = new FilaChamados();
    private PilhaHistorico historico = new PilhaHistorico();
    private ArvoreChamados arvore = new ArvoreChamados();
    private Scanner scanner = new Scanner(System.in);

    public void executar() {
        int opcao;

        do {
            System.out.println("\n===== SUPORTE TÉCNICO =====");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Listar usuários");
            System.out.println("3. Abrir chamado");
            System.out.println("4. Atender chamado");
            System.out.println("5. Listar fila de chamados");
            System.out.println("6. Histórico de atendimentos");
            System.out.println("7. Buscar chamado por código");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    usuarios.listar();
                    break;
                case 3:
                    abrirChamado();
                    break;
                case 4:
                    atenderChamado();
                    break;
                case 5:
                    fila.listar();
                    break;
                case 6:
                    historico.listar();
                    break;
                case 7:
                    buscarChamado();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private void cadastrarUsuario() {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Setor: ");
        String setor = scanner.nextLine();

        usuarios.adicionar(new Usuario(id, nome, setor));
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void abrirChamado() {
        System.out.print("Código do chamado: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID do usuário: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = usuarios.buscarPorId(idUsuario);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Descrição do problema: ");
        String descricao = scanner.nextLine();

        System.out.print("Prioridade (Baixa/Média/Alta): ");
        String prioridade = scanner.nextLine();

        Chamado chamado = new Chamado(codigo, descricao, usuario, prioridade);
        fila.adicionar(chamado);
        arvore.inserir(chamado);

        System.out.println("Chamado registrado com sucesso!");
    }

    private void atenderChamado() {
        if (fila.estaVazia()) {
            System.out.println("Nenhum chamado pendente.");
            return;
        }

        Chamado atendido = fila.atender();
        historico.registrar(atendido);

        System.out.println("Chamado atendido:");
        System.out.println(atendido);
    }

    private void buscarChamado() {
        System.out.print("Digite o código: ");
        int codigo = scanner.nextInt();

        Chamado chamado = arvore.buscar(codigo);
        if (chamado != null) {
            System.out.println(chamado);
        } else {
            System.out.println("Chamado não encontrado.");
        }
    }
}
