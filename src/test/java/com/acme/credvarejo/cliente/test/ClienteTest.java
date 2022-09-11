package com.acme.credvarejo.cliente.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;


import org.junit.Test;

import com.acme.credvarejo.ado.cliente.RepositorioCliente;
import com.acme.credvarejo.cliente.Cliente;
import com.acme.credvarejo.cliente.ControladorCliente;
import com.acme.credvarejo.cliente.Cpf;
import com.acme.credvarejo.ado.conta.InterfaceRepositorioContaCrediario;
import com.acme.credvarejo.ado.conta.RepositorioMovimentoCrediario;
import com.acme.credvarejo.conta.*;
import com.acme.credvarejo.contaCrediario.ContaCrediarioEspecial;
import com.acme.credvarejo.ado.conta.InterfaceRepositorioMovimentoCrediario;
import com.acme.credvarejo.ado.conta.RepositorioContaCrediario;

import static org.junit.Assert.assertEquals;

public class ClienteTest {
	
	Cpf cpfTeste = new Cpf(2017256900);
	
	@Test
	public void isCpf_False() {
		assertEquals(false, cpfTeste.isCPF("11111111111"));
	}
	
	@Test
	public void isCpf_True() {
		assertEquals(true, cpfTeste.isCPF("20172569001"));
	}
	
	@Test
	public void printarCpf() {
		assertEquals("201.725.690-01", cpfTeste.imprimeCPF("20172569001"));
	}
	
	@Test
	public void getNumero() {
		assertEquals(2017256900, cpfTeste.getNumero());
	}
	
	@Test
	public void setNumero() {
		cpfTeste.setNumero(390438770);
		assertEquals(390438770, cpfTeste.getNumero());
	}
	
	@Test
	public void testaDigitoMod() {
		assertEquals(cpfTeste.calculaDigitoMod11("15", 3, 5, false), "2");
	}
	

	
	Date data = new Date();
	Cliente clienteTeste = new Cliente(cpfTeste, "lucas teixeira", 22, data, 5000, 1);
	RepositorioCliente repositorio = new RepositorioCliente(0);
	ControladorCliente controllerCliente = new ControladorCliente(repositorio);
	
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@Test
	public void testaIncluir() {
		controllerCliente.incluir(clienteTeste);
	}
	
	
	@Test
	public void pegaPrimeiroNomeDoCliente() {
		assertEquals("lucas", clienteTeste.getPrimeiroNome());
	}
	
	@Test
	public void pegaSegundoNomeDoCliente() {
		assertEquals("teixeira", clienteTeste.getSegundoNome());
	}
	
	@Test
	public void pegaCpfDoCliente() {
		assertEquals(2017256900, clienteTeste.getCpf().getNumero());
	}
	
	@Test
	public void pegaNomeDoCliente() {
		assertEquals("lucas teixeira", clienteTeste.getNome());
	}
	
	@Test
	public void pegaIdadeDoCliente() {
		assertEquals(22, clienteTeste.getIdade());
	}
	
	@Test
	public void pegaClienteDesde() {
		assertEquals(data, clienteTeste.getClienteDesde());
	}
	
	@Test
	public void pegaRendaDoCliente() {
		assertEquals(5000,0, clienteTeste.getRenda());
	}
	
	@Test
	public void pegaSexoDoCliente() {
		assertEquals(1, clienteTeste.getSexo());
	}
	
	@Test
	public void defineSexoDoCliente() {
		clienteTeste.setSexo(0);
		assertEquals(0, clienteTeste.getSexo());
	}
	
	@Test
	public void pegaMascDoCliente() {
		assertEquals(0, clienteTeste.getMasc());
	}
	
	@Test
	public void pegaFemDoCliente() {
		assertEquals(1, clienteTeste.getFem());
	}
	
	@Test
	public void defineNomeDoCliente() {
		clienteTeste.setNome("raimundo jhones");
		assertEquals("raimundo jhones", clienteTeste.getNome());
	}
	
	@Test
	public void defineIdadeDoCliente() {
		clienteTeste.setIdade(24);
		assertEquals(24, clienteTeste.getIdade());
	}
	
	@Test
	public void defineClienteDesde() {
		clienteTeste.setClienteDesde(data);
		assertEquals(data, clienteTeste.getClienteDesde());
	}
	
	@Test
	public void defineRendaDoCliente() {
		clienteTeste.setRenda(6000);
		assertEquals(6000,0, clienteTeste.getRenda());
	}
	
	@Test
	public void pegaChave() {
		String cpf = String.valueOf((clienteTeste.getCpf().getNumero()));
		assertEquals(cpf, clienteTeste.getChave());
	}
	
	@Test
	public void clienteNaoValida() {
		clienteTeste.setSexo(4);
		clienteTeste.validar();
		assertEquals("", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void clienteValida() {
		clienteTeste.validar();
		assertEquals("", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void defineChaveRegistro() {
		clienteTeste.setChave("1234");
		assertEquals("2017256900", clienteTeste.getChave());
	}
	
	
	Cpf cpfTeste1 = new Cpf(627747580);
	Cliente novoCliente = new Cliente(cpfTeste1, "marcos marley", 21, data, 7000, 0);
	
	@Test
	public void alteraClienteRepo() {
		controllerCliente.alterar(627747580, "natanzinho show");
	}
	
	@Test
	public void excluirClienteRepo() {
		controllerCliente.incluir(novoCliente);
		controllerCliente.excluir(627747580);
	}
	
	@Test
	public void buscaClientePorChave() {
		controllerCliente.buscar(627747580);
	}
	
	@Test public void buscaTodos() {
		Date dataNova = new Date();
		Cpf cpfNovo = new Cpf(760539180);
		Cliente clienteAgora = new Cliente(cpfNovo, "merlin silva", 19, dataNova, 15000, 0);
		controllerCliente.incluir(clienteAgora);
		outputStreamCaptor.reset();
		repositorio.buscarTodos();
		assertEquals("", outputStreamCaptor.toString());
	}
	
	IdentificadorContaCrediario identificarTeste = new IdentificadorContaCrediario(23685852L);
	@Test
	public void verificaValidadeDoDigito() {
		assertEquals(false, identificarTeste.verificarValidadeDigito(23685852));
	}
	
	@Test
	public void pegaNumeroDoIdentificador() {
		assertEquals(23685852, identificarTeste.getNumero());
	}
	
	@Test
	public void defineNumeroDoIdentificador() {
		identificarTeste.setNumero(21675251);
		assertEquals(21675251, identificarTeste.getNumero());
	}
	
	@Test
	public void calculaDigitoVerificador() {
		identificarTeste.calcularDigitoVerificador();
	}
	
	ContaCrediarioEspecial contaCrediarioEspecial = new ContaCrediarioEspecial(identificarTeste, clienteTeste, 40, 1000, 25, false, 0.5, 0);
	@Test
	public void isAtivaTeste() {
		assertEquals(false, contaCrediarioEspecial.isAtiva());
	}
	
	@Test
	public void isReativado() {
		contaCrediarioEspecial.reativar();
		assertEquals(true, contaCrediarioEspecial.isAtiva());
	}
	
	@Test
	public void efetuaPagamentoTeste() {
		contaCrediarioEspecial.efetuarPagamento(2000);
	}
	
	@Test
	public void efetuaPagamentoSobrecarga() {
		contaCrediarioEspecial.efetuarPagamento(2000, 0.5);
	}
	
	@Test
	public void pegaPercentualDesconto() {
		assertEquals(0,0, contaCrediarioEspecial.getPercentualDeDesconto());
	}
	
	@Test
	public void definePercentualDesconto() {
		contaCrediarioEspecial.setPercentualDeDesconto(0.6);
		assertEquals(0,0, contaCrediarioEspecial.getPercentualDeDesconto());
	}
	
	@Test
	public void pegaPontosAcumulados() {
		assertEquals(0, contaCrediarioEspecial.getPontosAcumulados());
	}
	
	@Test
	public void definePontosAcumulados() {
		contaCrediarioEspecial.setPontosAcumulados(20);
		assertEquals(20, contaCrediarioEspecial.getPontosAcumulados());
	}
	
	InterfaceRepositorioContaCrediario repositorioConta = new RepositorioContaCrediario();
	ControladorContaCrediario controllerContaCrediario =  new ControladorContaCrediario(repositorioConta);
	
	@Test
	public void incluirClienteContaCrediario() {
		controllerContaCrediario.inserir(clienteTeste, 5000, 627747580);
	}
	
	@Test
	public void alteraContaCrediario() {
		repositorioConta.alterar(identificarTeste, 21);
	}
	
	@Test
	public void excluiContaCrediario() {
		repositorioConta.excluir(identificarTeste);
	}
	
	@Test
	public void testaBuscaPorChave() {
		repositorioConta.buscarPorChave(identificarTeste);
	}
	
	@Test
	public void testaBuscarTodos() {
		repositorioConta.buscarTodos();
	}
	
	InterfaceRepositorioMovimentoCrediario repositorioMovimentoCrediario = new RepositorioMovimentoCrediario();
	
	
	

}