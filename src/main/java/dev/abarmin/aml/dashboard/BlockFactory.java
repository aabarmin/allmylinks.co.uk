package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.dashboard.block.avatar.AvatarBlockProps;
import dev.abarmin.aml.dashboard.block.header.HeaderBlockProps;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.BlockType;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.block.header.HeaderLevel;
import dev.abarmin.aml.dashboard.block.header.TextAlignment;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class BlockFactory {
  private final BlockRepository blockRepository;

  public Block createBlock(BlockType type, Page page) {
    return switch (type) {
      case HEADER_BLOCK -> createHeaderBlock(page);
      case AVATAR_BLOCK -> createAvatarBlock(page);
    };
  }

  private Block createHeaderBlock(Page page) {
    return new Block(
      null,
      page.id(),
      BlockType.HEADER_BLOCK,
      nextOrder(page),
      false,
      HeaderBlockProps.builder()
        .level(HeaderLevel.H1)
        .text("Test header")
        .alignment(TextAlignment.CENTER)
        .build(),
      Instant.now(),
      null
    );
  }

  private Block createAvatarBlock(Page page) {
    return new Block(
      null,
      page.id(),
      BlockType.AVATAR_BLOCK,
      nextOrder(page),
      false,
      AvatarBlockProps.builder()
        .build(),
      Instant.now(),
      null
    );
  }

  private int nextOrder(Page page) {
    final List<Block> notDeleted = blockRepository.findAllByPageId(page.id())
      .stream()
      .filter(Predicate.not(Block::isDeleted))
      .toList();

    return notDeleted.size() + 1;
  }
}
