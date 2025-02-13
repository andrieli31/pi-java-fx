package application;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import controle.VendedorDAO;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import modelo.Vendedor;

public class ControllerTableViewFuncionarios implements Initializable {

	@FXML
	private SplitPane SlipPaneConfigurações;

	@FXML
	private StackPane StackPanePerfil;

	@FXML
	private Button bntCadastrar;

	@FXML
	private Button btnConfiguracoes;

	@FXML
	private Button btnDashboard;

	@FXML
	private Button btnFornecedores;

	@FXML
	private Button btnFuncionarios;

	@FXML
	private Button btnLocacao;

	@FXML
	private Button btnPerfil;

	@FXML
	private ImageView btnPesquisa;

	@FXML
	private Button btnPesquisar;

	@FXML
	private Button btnProdutos;

	@FXML
	private Button btnSair;

	@FXML
	private Button btnUsuarios;

	@FXML
	private TextField txtPesquisa;
	@FXML
	private ImageView imgDefaultConfiguracoes;

	@FXML
	private ImageView imgDefaultDashboard;

	@FXML
	private ImageView imgDefaultFornecedores;

	@FXML
	private ImageView imgDefaultFuncionarios;

	@FXML
	private ImageView imgDefaultLocacao;

	@FXML
	private ImageView imgDefaultProdutos;

	@FXML
	private ImageView imgDefaultUsuarios;

	@FXML
	private ImageView imgFotoPerfil;

	@FXML
	private ImageView imgLogo;

	@FXML
	private ImageView imgSair;

	@FXML
	private Label lblFornecedores;

	@FXML
	private Label lblFuncao;

	@FXML
	private Label lblNomeVendedor;

	@FXML
	private Pane panelConfiguracoes;

	@FXML
	private TableView<Vendedor> tableFuncionario;

	@FXML
	private TableColumn<Vendedor, String> columnCargo;

	@FXML
	private TableColumn<Vendedor, Integer> columnIdVendedor;

	@FXML
	private TableColumn<Vendedor, String> columnNome;

	@FXML
	private TableColumn<Vendedor, String> columnSobrenome;

	@FXML
	private TableColumn<Vendedor, Double> columnSalario;
	@FXML
	private TableColumn<Vendedor, String> columnAcoes;

	private ObservableList<Vendedor> obsVendedores;

	Vendedor vendedor = new Vendedor();
	VendedorDAO dao = new VendedorDAO();

	VendedorDAO forDao = new VendedorDAO();

	public void tblViewDivergenciaSearchFunc() {
		tableFuncionario.getItems().clear();
		columnCargo.setCellValueFactory(
				cellData -> new SimpleObjectProperty<>(cellData.getValue().getTipoAcesso().toString()));
		columnIdVendedor
				.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getId_vendedor()));
		columnNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
		columnSobrenome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSobrenome()));
		columnSalario.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSalario()));

		ObservableList<Vendedor> obsVendedore = FXCollections.observableArrayList(forDao.listar());
		tableFuncionario.setItems(obsVendedore);
		;
	}



	@FXML
	void abrirListCategorias(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/Categorias.fxml"));
			Parent root = loader.load();

			ListViewController controllerNovaTela = loader.getController();

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

	@FXML
	void abrirListClientes(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/ListClientes.fxml"));
			Parent root = loader.load();

			ControllerListClientes controllerNovaTela = loader.getController();

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

	@FXML
	void abrirListFornecedores(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/Fornecedores.fxml"));
			Parent root = loader.load();

			ControllerListFornecedores controllerNovaTela = loader.getController();

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

	@FXML
	void abrirListLocacoes(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/Locacoes.fxml"));
			Parent root = loader.load();

			ControllerListLocacoes controllerNovaTela = loader.getController();

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

	@FXML
	void abrirListPordutos(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/Produtos.fxml"));
			Parent root = loader.load();

			ControllerListProdutos controllerNovaTela = loader.getController();

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

	@FXML
	void abrirTelaConfiguracoes(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/Configuracoes.fxml"));
			Parent root = loader.load();

			//ControllerConfiguracoes controllerNovaTela = loader.getController();

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

	@FXML
	void abrirTelaDashboard(ActionEvent event) {
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

	@FXML
	private void sair(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/Login.fxml"));
			Parent root = loader.load();

			ControllerLogin controllerNovaTela = loader.getController();

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

	// Formata a cédula do salário para o padrão PT-BR (R$ 0000,000)
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		columnIdVendedor
				.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getId_vendedor()));
		columnNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
		columnSobrenome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSobrenome()));
		columnCargo.setCellValueFactory(
				cellData -> new SimpleObjectProperty<>(cellData.getValue().getTipoAcesso().toString()));

		// Configura a formatação da célula da coluna de salário
		columnSalario.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getSalario()));

		// Usa um StringConverter (Classe do FXML responsável por facilitar na conversão
		// de tipos Long e Double pra String)
		// para formatar o valor do salário como moeda

		StringConverter<Double> currencyConverter = new StringConverter<>() {
			private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

			@Override
			public String toString(Double value) {
				if (value == null) {
					return "";
				}

				return currencyFormat.format(value);
			}

			@Override
			public Double fromString(String string) {
				return null;
			}

		};

		// Definição e criação das células na coluna
		// Expressão lambda para simplificar a criação de células
		columnSalario.setCellFactory(tc -> new TableCell<Vendedor, Double>() {
			@Override
			protected void updateItem(Double salario, boolean empty) {
				super.updateItem(salario, empty);
				// Validação caso a coluna de salário esteja vazia, a cédula é setada como nullo
				// ou " "
				if (empty || salario == null) {

					setText("");
				} else {
					// Trecho do código que faz a formatação do valor dentro da cédula
					setText(currencyConverter.toString(salario));
				}
			}

		});

		columnAcoes.setCellFactory(new Callback<TableColumn<Vendedor, String>, TableCell<Vendedor, String>>() {
			@Override
			public TableCell<Vendedor, String> call(TableColumn<Vendedor, String> param) {
				return new TableCell<>() {

					// declarando as variaveis dos dois botoes
					private final Button viewButton = new Button();
					private final Button editButton = new Button();

					private final HBox buttonContainer = new HBox(viewButton, editButton);

					{
						buttonContainer.setSpacing(10);

						ImageView viewImage = new ImageView(
								new Image(getClass().getResourceAsStream("/imgs/editar.png")));
						viewImage.setFitHeight(16);
						viewImage.setFitWidth(16);
						viewButton.setStyle("-fx-background-color:  #001C52; -fx-text-fill: white;");
						viewButton.setGraphic(viewImage);
						viewButton.setOnAction(event -> {
							Vendedor vendedor = getTableView().getItems().get(getIndex());
							String idVende = vendedor.getId_vendedor().toString();

							try {
								FXMLLoader loader = new FXMLLoader(
										getClass().getResource("/visao/Edicao_funcionario.fxml"));
								Parent root = loader.load();
								ControllerEdicaoFuncionario controllerNovaTela = loader.getController();

								controllerNovaTela.setVendedor(vendedor);

								Scene scene = new Scene(root);
								Stage stage = new Stage();
								stage.setScene(scene);

								// atualizar a lsitagem depois do alter
								stage.setOnCloseRequest((EventHandler<WindowEvent>) new EventHandler<WindowEvent>() {
									public void handle(WindowEvent we) {
										tblViewDivergenciaSearchFunc();// metodo p popular o tableView
									}
								});

								stage.show();
							} catch (IOException e) {
								e.printStackTrace();
							}

							System.out.println("botao de edição clicado");
						});

						ImageView editImage = new ImageView(
								new Image(getClass().getResourceAsStream("/imgs/excluir.png")));
						editImage.setFitHeight(16);
						editImage.setFitWidth(16);
						editButton.setGraphic(editImage);
						editButton.setStyle("-fx-background-color: red;");
						editButton.setOnAction(event -> {

							Vendedor vendedor = getTableView().getItems().get(getIndex());
							if (dao.excluir(vendedor)) {

								// FALTA ADICIOANR POP UP DE CONFIRMAR EXLCUSAO

								getTableView().getItems().remove(vendedor);
								System.out.println("vendedor excluido c sucesso");
							} else {
								System.out.println("falha ao excluir vendedor");
							}
							System.out.println("botao de delete clicadoo");
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

		carregarVendedores();
	}

	public void carregarVendedores() {
		VendedorDAO dao = new VendedorDAO();
		ArrayList<Vendedor> vendedores = dao.listar();

		obsVendedores = FXCollections.observableArrayList(vendedores);
		tableFuncionario.setItems(obsVendedores);
	}

	@FXML
	void abrirListFuncionarios(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/Funcionarios.fxml"));
			Parent root = loader.load();

			ControllerTableViewFuncionarios controllerNovaTela = loader.getController();

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

	@FXML
	void abrirTelaCadastrarFuncionario(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/visao/Cadastro_funcionario.fxml"));
			Parent root = loader.load();

			TestController controllerNovaTela = loader.getController();

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
