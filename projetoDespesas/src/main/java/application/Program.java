package application;

import dao.CategoriaDAO;
import dao.DespesaDAO;
import model.Categoria;
import model.CategoriaSoma;
import model.Despesas;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DespesaDAO despesaDAO = new DespesaDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Locale localeBR = new Locale("pt", "BR");
        NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);

        int opcao = 0;

        while (opcao != 5) {
            System.out.println("\n=== CONTROLE DE DESPESAS ===");
            System.out.println("1 - Nova Despesa");
            System.out.println("2 - Ver Extrato");
            System.out.println("3 - Relatório por Categoria"); // Opção Nova
            System.out.println("4 - Excluir Histórico");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            if (sc.hasNextInt()) {
                opcao = sc.nextInt();
                sc.nextLine();
            } else {
                sc.next();
                continue;
            }

            if (opcao == 1) {
                System.out.println("\n--- NOVA DESPESA ---");
                List<Categoria> cats = categoriaDAO.listAll();
                for (Categoria c : cats) {
                    System.out.println(c.getId() + " - " + c.getName());
                }
                System.out.print("ID da Categoria: ");
                int idCat = sc.nextInt();
                sc.nextLine();

                Categoria c = new Categoria();
                c.setId(idCat);

                System.out.print("Descrição: ");
                String desc = sc.nextLine();

                System.out.print("Valor: ");
                double valor = sc.nextDouble();

                System.out.print("Data (dd/MM/aaaa): ");
                String dataStr = sc.next();
                LocalDate data = LocalDate.parse(dataStr, fmt);

                Despesas d = new Despesas();
                d.setDescricao(desc);
                d.setValor(valor);
                d.setData(data);
                d.setCategoria(c);
                despesaDAO.save(d);

                System.out.println("Despesa cadastrada!");

            } else if (opcao == 2) {
                System.out.println("\n--- EXTRATO DE GASTOS ---");
                List<Despesas> despesas = despesaDAO.findAll();
                double total = 0.0;

                for (Despesas d : despesas) {
                    System.out.println(
                            d.getData().format(fmt) + " | " +
                                    d.getDescricao() + " | " +
                                    dinheiro.format(d.getValor())
                    );
                    total += d.getValor();
                }
                System.out.println("---------------------------------");
                System.out.println("TOTAL GERAL: " + dinheiro.format(total));

            } else if (opcao == 3) {
                System.out.println("\n--- GASTOS POR CATEGORIA ---");
                List<CategoriaSoma> listaRelatorio = despesaDAO.buscarTotalPorCategoria();

                for (CategoriaSoma item : listaRelatorio) {
                    System.out.println(
                            item.getNomeCategoria() + ": " +
                                    dinheiro.format(item.getTotalGasto())
                    );
                }

            } else if (opcao == 4) {
                System.out.println("\n!!! PERIGO !!! Apagar tudo? (s/n): ");
                char confirmacao = sc.next().charAt(0);
                if (confirmacao == 's' || confirmacao == 'S') {
                    despesaDAO.limparTudo();
                    System.out.println("Histórico apagado.");
                }
            }
        }
        System.out.println("Programa encerrado.");
        sc.close();
    }
}