package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TrelloConfigTest {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void trelloApiEndpointIsNotNullTest() {
        assertThat(this.trelloConfig.getTrelloApiEndpoint()).isNotNull();
    }

    @Test
    public void trelloAppKeyIsNotNullTest() {
        assertThat(this.trelloConfig.getTrelloAppKey()).isNotNull();
    }

    @Test
    public void trelloTokenIsNotNullTest() {
        assertThat(this.trelloConfig.getTrelloToken()).isNotNull();
    }

    @Test
    public void trelloUserIsNotNullTest() {
        assertThat(this.trelloConfig.getTrelloUser()).isNotNull();
    }

}