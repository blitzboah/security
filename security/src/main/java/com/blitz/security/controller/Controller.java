package com.blitz.security.controller;

import com.blitz.security.model.Games;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    List<Games> gamesList = new ArrayList<>(List.of(new Games("rdr2", 1199),
            new Games("gta sa", 699)));

    @GetMapping("/")
    public String hello(){
        return "hello!";
    }

    @GetMapping("/games")
    public List<Games> listGames(){
        return gamesList;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/games")
    public List<Games> addGames(@RequestBody Games game){
        gamesList.add(game);
        return gamesList;
    }
}
