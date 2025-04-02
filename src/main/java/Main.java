import br.edu.fatecpg.tecprog.model.Post;
import br.edu.fatecpg.tecprog.service.ConsumoAPI;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String dadosRequisicao = ConsumoAPI.obterDados("https://jsonplaceholder.typicode.com/posts");

        Gson g = new Gson();
        // desserializando o json para um objeto post
        List<Post> posts = g.fromJson(dadosRequisicao, new TypeToken<List<Post>>(){}.getType());

        for(int i = 0; i<posts.toArray().length; i++){
            System.out.println(posts.get(i));
        }
        List<Post> postsComNihil = posts.stream()
                .filter(p -> p.getBody().contains("nihil"))
                .toList();
        System.out.println("Todos os post com Nihil\n");
        postsComNihil.forEach(System.out::println);

        List<Post> postsComNihilOrdenado = posts.stream()
                .filter(post -> post.getBody().contains("nihil"))
                .sorted(Comparator.comparingInt(Post::getId))
                .toList();
        System.out.println("Todos os posts com Nihil ordenados por id: \b");
        postsComNihilOrdenado.forEach(System.out::println);

        List<Integer> listaUsuarios = posts.stream()
                .map(Post::getUserId)
                .distinct()
                .toList();

        listaUsuarios.forEach(userId ->{
            List<Post> postsDoUsuário = posts.stream()
                    .filter(post -> post.getUserId() == userId)
                    .toList();
            System.out.println("Identificador do usuário: "+userId+"\n");
            postsDoUsuário.forEach(System.out::println);
        });

        List<String> titulosPostsFiltrados = postsComNihil.stream()
                        .map(Post::getTitle)
                                .toList();

        System.out.println("Títulos dos posts filtrados (que contenham Nihil)");
        titulosPostsFiltrados.forEach(System.out::println);
    }
}
