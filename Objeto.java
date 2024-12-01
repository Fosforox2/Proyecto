package Proyecto;

public class Objeto {
    private String name;
    private int tier;
    private int cantidad;
    private int damage;  // Nueva propiedad: daño (puede ser 0 para objetos sin daño)

    // Constructor actualizado con el cuarto parámetro (daño)
    public Objeto(String name, int tier, int cantidad, int damage) {
        this.name = name;
        this.tier = tier;
        this.cantidad = cantidad;
        this.damage = damage;
    }
    // Getters y Setters para la nueva propiedad "damage"
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    // Métodos existentes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void mostrarDetalles() {
        System.out.println("Nombre: " + name);
        System.out.println("Tier: " + tier);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Daño: " + damage);  // Mostrar el daño si es relevante
    }
}
