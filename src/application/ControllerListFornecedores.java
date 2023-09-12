package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controle.FornecedorDAO;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.Fornecedor;
import javafx.scene.image.Image;

import javafx.scene.control.TextField;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;



public class ControllerListFornecedores implements Initializable {

	@FXML
	private SplitPane SlipPaneConfigurações;

	@FXML
	private Button bntDashboard;

	@FXML
	private Button btnProdutos;

	@FXML
	private Button btnLocacao;

	@FXML
	private Button btnFuncionarios;

	@FXML
	private Button btnUsuarios;

	@FXML
	private Button btnFornecedores;

	@FXML
	private Button btnConfiguracoes;

	@FXML
	private Pane panelFornecedores;

	@FXML
	private Label lblFornecedores;

	@FXML
	private TableView<Fornecedor> tableFornecedores;

	@FXML
	private TableColumn<Fornecedor, Long> columnCnpj;

	@FXML
	private TableColumn<Fornecedor, String> columnNome;

	@FXML
	private TableColumn<Fornecedor, Long> columnTelefone;

	@FXML
	private TableColumn<Fornecedor, String> columnEndereco;

	@FXML
	private TableColumn<Fornecedor, String> columnAtividade;

	@FXML
	private TableColumn<Fornecedor, String> columnAcoes;

	private ObservableList<Fornecedor> obsFornecedores;

	@FXML
	private Button bntCadastrar;
	

	   @FXML
	    private TextField txtBusca;

	   
	   private String textoFiltro;
	   
	   
	   

	@FXML
	public void sair(ActionEvent event) {
		// Lógica para sair do aplicativo
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	    
	    
	    
	    
		FornecedorDAO dao = new FornecedorDAO();
		tableFornecedores.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		var f = dao.listar();
  //inicio do filtro de busca
		obsFornecedores = FXCollections.observableArrayList(f);

        
        
		textoFiltro = txtBusca.getText().toLowerCase();
		//tipo de filtro de listagem
		FilteredList<Fornecedor> filteredData = new FilteredList<>(obsFornecedores, fornecedor -> {
		    // Substitua 'getNome()' pelo método que retorna o campo que você deseja filtrar
		    String searchString = textoFiltro.toLowerCase(); // Converta a palavra digitada para minúsculas
		    String fornecedorNome = fornecedor.getNome().toLowerCase(); // Converta o campo para minúsculas
		    
		    // Realize o filtro com base na palavra chave
		    return fornecedorNome.contains(searchString);
		});


        // Vinculando o filtro de listagem à tabela de fornecedores
        SortedList<Fornecedor> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableFornecedores.comparatorProperty());
        tableFornecedores.setItems(sortedData);

        txtBusca.textProperty().addListener((observable, oldValue, newValue) -> {
        	
        	
            // Este trecho será executado sempre que o texto no campo de busca mudar
            filteredData.setPredicate(fornecedor -> {
                if (newValue == null || newValue.isEmpty()) {
                	System.out.println("novo valor: "+newValue);
                    return true; // Mostra todos os dados quando não houver filtro
                }

                // conversão do texto do filtro para valores sem maiuscula para busca na lsitagem
                String textoFiltro = newValue.toLowerCase();

                // busca de dados baseada nos valores armazenados na entidade fornecedor 
                
                
                System.out.println(textoFiltro);
                System.out.println(fornecedor.getNome().toLowerCase());
                return fornecedor.getNome().toLowerCase().contains(textoFiltro) ||
                        String.valueOf(fornecedor.getCnpj()).contains(textoFiltro) ||
                        String.valueOf(fornecedor.getTelefone()).contains(textoFiltro);            });
        });

      
        final String textoPesquisa = txtBusca.getText();

        // O Runnable é responsável pela busca de dados com um atraso de certos segundos
        final Runnable pesquisa = () -> {
            // Inside the lambda, use the previously defined variable
            filteredData.setPredicate(fornecedor -> {
                if (textoPesquisa == null || textoPesquisa.isEmpty()) {
                    return true; // Mostra todos os dados quando não houver filtro
                }

                // conversão do texto do filtro para valores sem maiuscula para busca na listagem
                String textoFiltro = textoPesquisa.toLowerCase();

                // busca de dados baseada nos valores armazenados na entidade fornecedor
                return fornecedor.getNome().toLowerCase().contains(textoFiltro) ||
                       String.valueOf(fornecedor.getCnpj()).contains(textoFiltro) ||
                       String.valueOf(fornecedor.getTelefone()).contains(textoFiltro);
            });
        };
       







        // Executar pesquisa após um pequeno atraso (300 milissegundos)
        txtBusca.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Platform.runLater(() -> {
                    if (txtBusca.getText().equals(newValue)) {
                        pesquisa.run();
                    }
                });
            }
        });
        
        

		
		columnCnpj.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCnpj()));
		columnNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
		columnAtividade.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAtividades()));

		
		//Método responsável pela criação dos botões de edição e exclusão de fornecedor dentro da tabela com o header 'Ações'
		
		columnAcoes.setCellFactory(new Callback<TableColumn<Fornecedor, String>, TableCell<Fornecedor, String>>() {
			@Override
			public TableCell<Fornecedor, String> call(TableColumn<Fornecedor, String> param) {
				return new TableCell<Fornecedor, String>() {
					
					//Declaração das variáveis dos dois botões
					private final Button viewButton = new Button();
					private final Button editButton = new Button();
					
					
					private final HBox buttonContainer = new HBox(viewButton, editButton);

					{
						buttonContainer.setSpacing(10); // Setta o espaçamento entre os botões

						//Estilização do botão de edição de funcionário, settando a imagem do lapis e a cor de fundo do botão
						ImageView viewImage = new ImageView(
								new Image(getClass().getResourceAsStream("/imgs/editar.png")));
						viewImage.setFitHeight(16);
						viewImage.setFitWidth(16);
						viewButton.setStyle("-fx-background-color:  #001C52; -fx-text-fill: white;");

						viewButton.setGraphic(viewImage);
						viewButton.setOnAction(event -> {
						
							
							//Método de acionamento do botão de edição (pendente a tela pra chamar para poder editar os dados existentes)
							Fornecedor fornecedor = getTableView().getItems().get(getIndex());
						    String cnpj = fornecedor.getCnpj().toString();

						    System.out.println("edição com o cnpj"+ cnpj);
						    
						    
							System.out.println("botao de edição clicado");
							

						
						
						
						
						
						
						});

						ImageView editImage = new ImageView(
								new Image(getClass().getResourceAsStream("/imgs/excluir.png")));
						editImage.setFitHeight(16);
						editImage.setFitWidth(16);
						editButton.setGraphic(editImage);
						editButton.setStyle("-fx-background-color: red;");
						editButton.setOnAction(event -> {
							
							Fornecedor fornecedor = getTableView().getItems().get(getIndex());
						
							if(dao.excluir(fornecedor)) {
								
							     getTableView().getItems().remove(fornecedor);
							        System.out.println("Fornecedor excluído com sucesso.");
							    
							
							
							
							
							} else {
							        System.out.println("Erro ao excluir o fornecedor.");
							    }
							
							
							System.out.println("botao de delete clicado");

						});
					}

					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);

						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(buttonContainer);
						}
					}
				};
			}
		});

		// pega o valor do endereço com base no id de endereço existente no back-end e
		// exibe somente a rua.
		// caso seja necessário mais informações a respeito do endereço, seguir o
		// exemplo existente
		// e ajustar. Por exemplo para o dado CEP do endereço cadastrado no id 1: 'cep =
		// fornecedor.getEnderecoId.getCep()'
		// para exibir tudo relacionado ao endereco, adicionar mais colunas específicas
		// para a entidade 'Endereco nessa tabela

		columnEndereco.setCellValueFactory(cellData -> {
			Fornecedor fornecedor = cellData.getValue();
			String rua = "";
			if (fornecedor.getEnderecoId() != null) {
				rua = fornecedor.getEnderecoId().getRua();
			}
			return new SimpleStringProperty(rua);
		});

		// Configuração da coluna de telefone com formatação
		columnTelefone.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTelefone()));

		columnTelefone.setCellFactory(tc -> new TableCell<Fornecedor, Long>() {

			// máscara responsável pela formatação de número de telefone com base no modelo
			// (99) 99999-9999
			@Override
			protected void updateItem(Long telefone, boolean empty) {

				super.updateItem(telefone, empty);

				// caso o numero venha vazio a mascara não será aplicada
				if (telefone == null || empty) {
					setText(null);
					// faz a formatação com base na quantidade de caracteres dentro do atributo
					// 'Telefone'da classe 'Fornecedores'
					// padrão de número de telefone com 11 dígitos no modelo Brasileiro (padrões do
					// tipo EUA não irão funcionar)
				} else {
					String telefoneStr = String.valueOf(telefone);
					if (telefoneStr.length() == 10) {
						setText("(" + telefoneStr.substring(0, 2) + ") " + telefoneStr.substring(2, 6) + "-"
								+ telefoneStr.substring(6));
					} else if (telefoneStr.length() == 11) {
						setText("(" + telefoneStr.substring(0, 2) + ") " + telefoneStr.substring(2, 7) + "-"
								+ telefoneStr.substring(7));
					} else {
						setText(telefoneStr);
					}
				}
			}
		});

		carregarFornecedores();
	}
	
	private void modalDeleteComSucesso() {
		
	}

	@FXML
	public void abrirTelaCadastroGemFornecedores(ActionEvent event) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/Cadastro_fornecedor.fxml"));
			Parent root = loader.load();

			ControllerCadastroFornecedores controllerNovaTela = loader.getController();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
//			// fecha a tela atual
			Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stageAtual.close();

			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Lógica para salvar os dados
	}

	public void carregarFornecedores() {
		FornecedorDAO dao = new FornecedorDAO();

		ArrayList<Fornecedor> fornecedores = dao.listar();

		obsFornecedores = FXCollections.observableArrayList(fornecedores);
		tableFornecedores.setItems(obsFornecedores);
	}

	public class TelefoneFormatter {
		public static String formatTelefoneBrasil(int telefone) {
			String telefoneStr = String.valueOf(telefone);
			if (telefoneStr.length() == 10) { // (XX) XXXX-XXXX
				return "(" + telefoneStr.substring(0, 2) + ") " + telefoneStr.substring(2, 6) + "-"
						+ telefoneStr.substring(6);
			} else if (telefoneStr.length() == 11) { // (XX) XXXXX-XXXX
				return "(" + telefoneStr.substring(0, 2) + ") " + telefoneStr.substring(2, 7) + "-"
						+ telefoneStr.substring(7);
			}
			return telefoneStr; // Retornar sem formatação se não corresponder a nenhum padrão
		}
	}

	@FXML
	void abrirDashboard(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/Dashboard.fxml"));
			Parent root = loader.load();

			ControllerDashboard controllerNovaTela = loader.getController();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			// fecha a tela atual
			Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stageAtual.close();

			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
