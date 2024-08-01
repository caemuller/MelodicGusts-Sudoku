package com.trabf.melodicgusts.Models.entities;

import com.trabf.melodicgusts.Models.Model;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void createPieces4x4() {
        Board board4x4= Model.getInstance().getBoard4x4();
        for (int i = 0; i < board4x4.getRows(); i++) {
            for (int j = 0; j < board4x4.getRows(); j++) {
                assertNotNull(board4x4.getPieces()[i][j]);
            }
        }
    }

    @Test
    void createPieces6x6() {
        Board board6x6= Model.getInstance().getBoard4x4();
        for (int i = 0; i < board6x6.getRows(); i++) {
            for (int j = 0; j < board6x6.getRows(); j++) {
                assertNotNull(board6x6.getPieces()[i][j]);
            }
        }
    }

//    @Test
//    void testRandomPiece() {
//
//    }
}