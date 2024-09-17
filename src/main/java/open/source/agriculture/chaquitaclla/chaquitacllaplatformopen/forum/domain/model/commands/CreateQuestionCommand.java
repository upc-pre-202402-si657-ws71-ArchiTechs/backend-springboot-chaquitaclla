package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands;



public record CreateQuestionCommand(Long categoryId, Long userId, String questionText) {
}
