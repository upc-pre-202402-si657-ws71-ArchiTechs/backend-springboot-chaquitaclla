package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands;



public record CreateAnswerCommand(Long userId, Long questionId, String answerText) {
}
