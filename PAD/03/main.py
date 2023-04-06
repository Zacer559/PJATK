import numpy as np
import pandas as pd


def main():
    # TASK A
    data = np.genfromtxt('PAD_03.csv', delimiter=';', dtype=[('ID', 'i4'), ('Country', 'U10'), ('owns_car', 'i1'),
                                                             ('owns_TV', 'i1'), ('owns_house', 'i1'),
                                                             ('owns_Phone', 'i1'),
                                                             ('gender', 'U1'), ('Age', 'i1')],
                         encoding='utf-8', names=True, missing_values=['', 'nan'], filling_values=None)

    # TASK B
    print(data)

    empty_records = []
    for row in data:
        if '' in row:
            empty_records.append(row['ID'])

    print(empty_records)

    # TASK C
    # I'm converting numpy table to pandas DataFrame because
    # it was impossible to properly insert a column into numpy table
    # I tried like 20 different approaches and all failed
    owned_goods = data['owns_car'] + data['owns_TV'] + data['owns_house'] + data['owns_Phone']
    df = pd.DataFrame(data)
    df['owned_goods'] = owned_goods
    print(df)

    # TASK D
    mean_wealth_men = round(df[df['gender'] == 'M']['owned_goods'].mean(), 2)
    mean_wealth_women = round(df[df['gender'] == 'K']['owned_goods'].mean(), 2)
    print(f"Average wealth for men: {mean_wealth_men}")
    print(f"Average wealth for women: {mean_wealth_women}")

    # TASK E
    grouped = df.groupby('Country')
    result = np.empty((0, 3), dtype=[('Country', 'U10'), ('Avg_owned_goods', 'f4'), ('Min_age', 'i4')])

    for country, group in grouped:
        if country!='':
            avg_owned_goods = round(group['owned_goods'].mean(), 2)
            min_age = df[df['Country'] == country]['Age'].min()
            result = np.append(result, np.array([(country, avg_owned_goods, min_age)], dtype=result.dtype))

    print(result)

    # TASK F
    np.savetxt('PAD_03_new.csv', result, delimiter=';', fmt='%s', header='Country;Avg_owned_goods;Min_age',
               comments='')


if __name__ == '__main__':
    main()
