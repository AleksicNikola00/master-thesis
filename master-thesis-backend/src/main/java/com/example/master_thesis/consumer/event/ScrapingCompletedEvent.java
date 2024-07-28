package com.example.master_thesis.consumer.event;

import lombok.Builder;

@Builder
public record ScrapingCompletedEvent(boolean hadAdditionalData) {
}
