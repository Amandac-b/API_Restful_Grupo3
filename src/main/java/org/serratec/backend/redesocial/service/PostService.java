package org.serratec.backend.redesocial.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.serratec.backend.redesocial.dto.PostDTO;
import org.serratec.backend.redesocial.model.Post;
import org.serratec.backend.redesocial.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<PostDTO> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
            .map(post -> new PostDTO(post.getId(), post.getConteudo(), post.getDataCriacao()))
            .collect(Collectors.toList());
    }

    public PostDTO findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return new PostDTO(post.getId(), post.getConteudo(), post.getDataCriacao());
    }

    public PostDTO save(PostDTO postDTO) {
        Post post = new Post();
        post.setConteudo(postDTO.getConteudo());
        try {
            post.setDataCriacao(LocalDate.now());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error setting dataCriacao", e);
        }
        post = postRepository.save(post);
        return new PostDTO(post.getId(), post.getConteudo(), post.getDataCriacao());
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}

    





