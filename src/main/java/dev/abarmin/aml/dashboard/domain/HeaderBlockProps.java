package dev.abarmin.aml.dashboard.domain;

public record HeaderBlockProps() implements BlockProps {
  @Override
  public BlockType type() {
    return BlockType.HEADER_BLOCK;
  }
}
