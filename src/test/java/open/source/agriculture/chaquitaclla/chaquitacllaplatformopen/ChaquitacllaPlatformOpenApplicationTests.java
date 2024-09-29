package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.application.internal.commandservices.SowingCommandServiceImpl;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates.Sowing;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.CreateSowingCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.UpdateSowingCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.CropId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.infrastructure.persistence.jpa.repositories.SowingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SowingCommandServiceImplTest {

    @Mock
    private SowingRepository sowingRepository;

    @Mock
    private CropRepository cropRepository;

    @InjectMocks
    private SowingCommandServiceImpl sowingCommandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleCreateSowingCommand() {
        // Arrange
        CreateSowingCommand command = new CreateSowingCommand(1, 100);
        Sowing sowing = new Sowing(new CropId(1L), 100);
        when(sowingRepository.save(any(Sowing.class))).thenReturn(sowing);

        // Act
        Long result = sowingCommandService.handle(command);

        // Assert
        assertNotNull(result);
        verify(sowingRepository, times(1)).save(any(Sowing.class));
    }

    @Test
    void testHandleUpdateSowingCommand() {
        // Arrange
        UpdateSowingCommand command = new UpdateSowingCommand(1L, 2, 200);
        Sowing existingSowing = new Sowing(new CropId(1L), 100);
        when(sowingRepository.existsById(command.Id())).thenReturn(true);
        when(sowingRepository.findById(command.Id())).thenReturn(Optional.of(existingSowing));
        when(sowingRepository.save(any(Sowing.class))).thenReturn(existingSowing);

        // Act
        Optional<Sowing> result = sowingCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(200, result.get().getAreaLand());
        verify(sowingRepository, times(1)).existsById(command.Id());
        verify(sowingRepository, times(1)).findById(command.Id());
        verify(sowingRepository, times(1)).save(any(Sowing.class));
    }
}