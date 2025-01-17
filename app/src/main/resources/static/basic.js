

$(document).ready(function () {
    showMovies();
})

///////////////////////////////////////////////////////////////////////////////////////////
function showMovies() {
    $.ajax({
        type: 'GET',
        url: '/api/movies',
        success: function(response) {
            $('#search-result-box').empty();

            for(let i=0; i<response.length; i++) {
                let movie = response[i];
                let tempHtml = addMovieItem(movie);
                $('#search-result-box').append(tempHtml);
            }
        }

    })
}

function addMovieItem(movie) {
    return `<div class="search-itemDto">
            <div class="search-itemDto-left">
                <img src="https://search.pstatic.net/common?type=o&size=176x264&quality=85&direct=true&src=https%3A%2F%2Fs.pstatic.net%2Fmovie.phinf%2F20250106_231%2F1736149174923sBbGV_JPEG%2Fmovie_image.jpg%3Ftype%3Dw640_2" alt="">
            </div>
            <div class="search-itemDto-center">
                <div>타이틀: ${movie.title}</div>
                <div>영상물 등급: ${movie.rating}</div>
                <div>개봉일: ${movie.releaseDate}</div>
                <div>이미지: ${movie.thumbnailImage}</div>
                <div>러닝 타임(분): ${movie.runningTime}</div>
                <div>영화 장르: : ${movie.genre}</div>
                <div>상영관 이름: : ${movie.theater}</div>
            </div>
        </div>`;
}

