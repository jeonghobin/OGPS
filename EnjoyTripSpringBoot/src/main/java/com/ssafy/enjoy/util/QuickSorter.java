package com.ssafy.enjoy.util;

import java.util.ArrayList;
import java.util.List;
import com.ssafy.enjoy.board.dto.FNBoardDto;

public class QuickSorter {
	public static List<FNBoardDto> quickSort(List<FNBoardDto> list) {
        if (list.size() <= 1) return list;
        FNBoardDto pivot = list.get(list.size() / 2);

        List<FNBoardDto> lesserList = new ArrayList<>();
        List<FNBoardDto> equalList = new ArrayList<>();
        List<FNBoardDto> greaterList = new ArrayList<>();

        for (FNBoardDto dto : list) {
            if (dto.getHit() > pivot.getHit()) lesserList.add(dto);
            else if (dto.getHit() < pivot.getHit()) greaterList.add(dto);
            else equalList.add(dto);
        }

        List<FNBoardDto> mergedList = new ArrayList<>();
        mergedList.addAll(quickSort(lesserList));
        mergedList.addAll(equalList);
        mergedList.addAll(quickSort(greaterList));
        return mergedList;
    }
}
