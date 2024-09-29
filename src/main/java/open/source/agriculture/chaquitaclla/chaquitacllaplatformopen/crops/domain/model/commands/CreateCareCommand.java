package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands;

import java.time.LocalDate;

public record CreateCareCommand(String description, LocalDate careDate) {
}