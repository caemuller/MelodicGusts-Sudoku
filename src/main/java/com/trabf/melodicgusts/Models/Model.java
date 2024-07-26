package com.trabf.melodicgusts.Models;

import com.trabf.melodicgusts.Models.entities.Board;
import com.trabf.melodicgusts.Models.entities.Piece;
import com.trabf.melodicgusts.Models.entities.Timer;
import com.trabf.melodicgusts.Models.entities.User;
import com.trabf.melodicgusts.Views.ViewFactory;

public class Model {
    private static Model model;
    private ViewFactory viewFactory;
    private final User user;
    private Piece piece;
    private Board board4x4;
    private Board board6x6;
    private Timer timer;

    public Model() {
        this.viewFactory = new ViewFactory();
        this.user = new User("");
        this.piece = new Piece();
        this.board4x4 = new Board(4, 4);
        this.board6x6 = new Board(6, 6);
        this.timer = new Timer(5);
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

    public Board getBoard4x4() {return board4x4;
    }

    public Board getBoard6x6() {return board6x6;
    }

    public Timer getTimer() {
        return timer;
    }
}
