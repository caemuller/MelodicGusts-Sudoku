package com.trabf.melodicgusts.Models;

import com.trabf.melodicgusts.Models.entities.Board;
import com.trabf.melodicgusts.Models.entities.Piece;
import com.trabf.melodicgusts.Models.entities.User;
import com.trabf.melodicgusts.Views.ViewFactory;

public class Model {
    private static Model model;
    private ViewFactory viewFactory;
    private final User user;
    private Piece piece;
    private Board board;

    public Model() {
        this.viewFactory = new ViewFactory();
        this.user = new User("");
        this.piece = new Piece();
        this.board = new Board(4, 4);
    }

    public static synchronized Model getInstance() {
        if(model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public User getUser() {return user;
    }

    public Piece getPiece() {return piece;
    }

    public Board getBoard() {return board;
    }
}
