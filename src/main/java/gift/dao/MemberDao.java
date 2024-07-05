package gift.dao;

import gift.vo.Member;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

/**
 * JdbcClient 이용한 DB 접속
 */
@Repository
public class MemberDao {

    private final JdbcClient jdbcClient;

    public MemberDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Member findMemberByEmailAndPassword(String email, String password) {
    String sql = "SELECT * FROM member WHERE email = :email and password = :password";
        return this.jdbcClient.sql(sql)
                .param("email", email)
                .param("password", password)
                .query(new MemberMapper()).single();
    }

    public Boolean addMember(Member member) {
        String sql = "INSERT INTO member (email, password) VALUES (:email, :password)";
        int resultRowNum = this.jdbcClient.sql(sql)
                .param("email", member.getEmail())
                .param("password", member.getPassword())
                .update();
        return resultRowNum == 1;
    }

}
