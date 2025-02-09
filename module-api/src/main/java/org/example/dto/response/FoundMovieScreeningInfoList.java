package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.dto.MovieScreeningInfo;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoundMovieScreeningInfoList {
    private List<MovieScreeningInfo> movieScreeningInfos;
}
