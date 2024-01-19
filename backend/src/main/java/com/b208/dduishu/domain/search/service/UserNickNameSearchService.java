package com.b208.dduishu.domain.search.service;

import com.b208.dduishu.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserNickNameSearchService {

    @PersistenceContext(type=PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @PostConstruct
    public void init() throws InterruptedException {
        buildSearchIndex();
    }

    @Transactional
    public void buildSearchIndex() throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();
    }

    public List<User> searchUserNickName(String keyword) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(User.class).get();

        Query query = queryBuilder.keyword().fuzzy()
                .withEditDistanceUpTo(2)
                .withPrefixLength(0)
                .onField("nickname").matching(keyword).createQuery();

        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, User.class);
        List<User> users = (List<User>) fullTextQuery.getResultList();

        return users;
    }
}
