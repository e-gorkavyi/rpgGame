import java.util.Optional;

public interface Seller {
    Optional<StorePosition> sell(String positionName) throws CloneNotSupportedException;
}
