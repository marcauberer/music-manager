package bartype;

import java.util.List;

public interface BarTypeRepository {
    BarType findBarTypeById(long id);

    List<BarType> findAllBarTypes();

    BarType findBarTypeBySongId(long songId);

    BarType save(BarType barType);

    void delete(long id);
}