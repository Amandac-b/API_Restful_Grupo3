package org.serratec.backend.redesocial.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.serratec.backend.redesocial.dto.PostDTO;
import org.serratec.backend.redesocial.exception.NotFoundException;
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
        return posts.stream().map(post -> new PostDTO(post.getId(), post.getConteudo(), post.getDataCriacao()))
                .collect(Collectors.toList());
    }

    public PostDTO findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post não encontrado"));
        return new PostDTO(post.getId(), post.getConteudo(), post.getDataCriacao());
    }

    public PostDTO createPost(Post post) {
        post.setDataCriacao(LocalDate.now());
        postRepository.save(post);
        return new PostDTO(post);
    }

    public void findAndDelete(Long id) {

        Optional<Post> postOpt = postRepository.findById(id);

        if (postOpt.isEmpty()) {
            throw new NotFoundException("O Post não foi encontrado");
        }

        postRepository.deleteById(id);
    }

    public PostDTO save (Long id, Post postAtualizado) {
        Optional<Post> postOpt = postRepository.findById(id);

        if (postOpt.isPresent()) {
            postAtualizado.setId(postOpt.get().getId());
            return new PostDTO(postRepository.save(postAtualizado));
            }

        throw new NotFoundException("O post não foi encontrado.");
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    public Post save(@Valid Post post) {
        return postRepository.save(post);
       }
    public boolean existsById(Long id) {
    return postRepository.existsById(id);
}

}