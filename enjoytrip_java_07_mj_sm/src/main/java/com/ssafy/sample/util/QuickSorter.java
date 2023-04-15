package com.ssafy.sample.util;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.board.model.BoardDto;

public class QuickSorter {
	public static List<BoardDto> quickSort(List<BoardDto> list) {
        if (list.size() <= 1) return list;
        BoardDto pivot = list.get(list.size() / 2);

        List<BoardDto> lesserList = new ArrayList<>();
        List<BoardDto> equalList = new ArrayList<>();
        List<BoardDto> greaterList = new ArrayList<>();

        for (BoardDto dto : list) {
            if (dto.getHit() > pivot.getHit()) lesserList.add(dto);
            else if (dto.getHit() < pivot.getHit()) greaterList.add(dto);
            else equalList.add(dto);
        }

        List<BoardDto> mergedList = new ArrayList<>();
        mergedList.addAll(quickSort(lesserList));
        mergedList.addAll(equalList);
        mergedList.addAll(quickSort(greaterList));
        return mergedList;
    }
}
