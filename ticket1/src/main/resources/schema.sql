DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS performance;

-- 공연 (performance) 테이블 생성
CREATE TABLE performance (
                             p_id VARCHAR(255) PRIMARY KEY,      -- 공연 ID
                             p_title VARCHAR(255) NOT NULL,      -- 공연 제목
                             p_place VARCHAR(255) NOT NULL,      -- 공연 장소
                             p_date DATETIME NOT NULL,           -- 공연 날짜 및 시간
                             p_price INT NOT NULL                -- 티켓 가격
);

-- 예매 (reservation) 테이블 생성
CREATE TABLE reservation (
                             r_id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 예매 ID (자동 증가)
                             u_name VARCHAR(255) NOT NULL,            -- 유저 이름
                             u_id VARCHAR(255) NOT NULL,              -- 유저 ID
                             r_spot VARCHAR(255) NOT NULL,            -- 좌석 번호
                             r_spot_status VARCHAR(255) NOT NULL,     -- 좌석 상태
                             r_phone VARCHAR(255) NOT NULL,           -- 예매자의 전화번호
                             r_email VARCHAR(255) NOT NULL,           -- 예매자의 이메일
                             r_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 예매 시간 (자동 생성)
                             p_date DATETIME NOT NULL,                -- 공연 날짜 및 시간
                             p_title VARCHAR(255) NOT NULL,           -- 공연 제목
                             p_place VARCHAR(255) NOT NULL,           -- 공연 장소
                             p_all_spot INT NOT NULL,
                             p_price INT NOT NULL,                    -- 티켓 가격
                             p_id VARCHAR(255) NOT NULL,              -- 공연 ID (외래키)
                             FOREIGN KEY (p_id) REFERENCES performance(p_id) ON DELETE CASCADE
);
