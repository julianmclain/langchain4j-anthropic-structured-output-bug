# langchain4j Anthropic Structured Output Bug

langchain4j 1.10.0 sends structured output requests using the deprecated `output_format` parameter. The Anthropic API now expects `output_config.format` instead, causing requests with `ResponseFormat` to fail.

## Error

```
InvalidRequestException: output_format: This field is deprecated.
Use 'output_config.format' instead.
```

## Reproduce

Requires Java 21 and an Anthropic API key.

```
ANTHROPIC_API_KEY=your-key ./gradlew run
```
