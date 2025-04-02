import br.edu.fatecpg.tecprog.service.ConsumoAPI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String dadosRequisicao = ConsumoAPI.obterDados("https://jsonplaceholder.typicode.com/posts");

        System.out.println(dadosRequisicao);
    }
}
